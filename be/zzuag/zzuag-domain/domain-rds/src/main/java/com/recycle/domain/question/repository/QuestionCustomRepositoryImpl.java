package com.recycle.domain.question.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recycle.domain.question.dto.QuestionWithReviewLikesByUserDTO;
import com.recycle.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.recycle.domain.question.entity.QQuestion.question;
import static com.recycle.domain.review.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class QuestionCustomRepositoryImpl implements QuestionCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Question> findQuestionsByTop10Reviewed() {
        return queryFactory.select(question)
                .from(question)
                .join(review).on(question.id.eq(review.metaData.question.id))
                .where(
                        review.createdAt.goe(LocalDateTime.now().minusDays(7)),
                        question.isDeleted.isFalse(),
                        review.isDeleted.isFalse()
                )
                .orderBy(question.metaData.reviewCount.desc(), question.createdAt.desc())
                .limit(10)
                .fetch();
    }

    @Override
    public Page<QuestionWithReviewLikesByUserDTO> getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable) {

        List<QuestionWithReviewLikesByUserDTO> questions = queryFactory.select(Projections.constructor(
                QuestionWithReviewLikesByUserDTO.class,
                        question.id,
                        question.content,
                        question.likeCount,
                        review.likeCount.sum().as("totalReviewLikes"),
                        review.id.count().as("reviewCount")
                ))
                .from(question)
                .join(review).on(review.id.eq(question.id))
                .where(review.userId.eq(userId)
                        .and(question.isDeleted.isFalse())
                        .and(review.isDeleted.isFalse()))
                .groupBy(question.id)
                .orderBy(review.likeCount.sum().desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = Optional.ofNullable(queryFactory.select(question.count())
                        .from(question)
                        .join(review).on(review.id.eq(question.id))
                        .where(review.userId.eq(userId)
                                .and(question.isDeleted.isFalse())
                                .and(review.isDeleted.isFalse()))
                        .fetchOne())
                .orElse(0L);

        return new PageImpl<>(questions, pageable, totalCount);
    }
}
