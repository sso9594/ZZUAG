package com.recycle.domain.question.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recycle.domain.question.dto.QuestionWithReviewLikesByUserDTO;
import com.recycle.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.recycle.domain.question.entity.QQuestion.question;
import static com.recycle.domain.question.entity.QQuestionFavorite.questionFavorite;
import static com.recycle.domain.review.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class QuestionCustomRepositoryImpl implements QuestionCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    @Description("최근 7일간 리뷰가 많이 달린 상위 10개 질문을 조회합니다.")
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
    @Description("사용자가 작성한 리뷰의 좋아요 수가 많은 순으로 질문을 조회합니다.")
    public Page<QuestionWithReviewLikesByUserDTO> getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable) {

        List<QuestionWithReviewLikesByUserDTO> questions = queryFactory.select(Projections.constructor(
                QuestionWithReviewLikesByUserDTO.class,
                        question.id,
                        question.metaData.title,
                        question.content,
                        question.likeCount,
                        review.likeCount.sum().as("totalReviewLikes"),
                        review.id.count().intValue().as("reviewCount")
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

    @Override
    @Description("사용자가 관심있어 하는 질문을 조회합니다.")
    public Page<Question> findUserInterestedQuestions(Long userId, Pageable pageable) {

        List<Question> questions = queryFactory
                .select(question)
                .from(question)
                .leftJoin(questionFavorite).on(questionFavorite.question.eq(question))
                .where(questionFavorite.userId.eq(userId))
                .orderBy(question.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = Optional.ofNullable(
                queryFactory.select(question.count())
                        .from(question)
                        .leftJoin(questionFavorite).on(questionFavorite.question.eq(question))
                        .where(questionFavorite.userId.eq(userId))
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(questions, pageable, totalCount);
    }

    @Override
    public Page<Question> findQuestionsByKeyword(String keyword, Pageable pageable) {
        List<Question> results = queryFactory
                .select(question)
                .from(question)
                .where(question.content.contains(keyword)
                        .or(question.metaData.title.contains(keyword)))
                .orderBy(question.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = Optional.ofNullable(
                queryFactory.select(question.count())
                        .from(question)
                        .where(question.content.contains(keyword)
                                .or(question.metaData.title.contains(keyword)))
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(results, pageable, totalCount);
    }


}
