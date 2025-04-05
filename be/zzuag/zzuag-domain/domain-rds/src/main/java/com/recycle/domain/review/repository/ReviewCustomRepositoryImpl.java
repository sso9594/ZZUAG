package com.recycle.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recycle.domain.review.dto.ReviewWithReviewLikesByUserRdsDTO;
import com.recycle.domain.review.dto.TopReviewedDTO;
import com.recycle.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.recycle.domain.question.entity.QQuestion.question;
import static com.recycle.domain.review.entity.QReview.review;
import static com.recycle.domain.review.entity.QReviewFavorite.reviewFavorite;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository{

    private final JPAQueryFactory queryFactory;
    @Override
    @Description("최근 7일간 좋아요가 많이 달린 상위 10개 리뷰를 조회합니다.")
    public List<TopReviewedDTO> getTopReviewedDTOs() {
        return queryFactory.select(Projections.constructor(
                        TopReviewedDTO.class,
                        review.id,
                        Expressions.stringTemplate("function('LEFT', {0}, {1})", question.content, 20),
                        review.content,
                        review.likeCount
                ))
                .from(review)
                .leftJoin(review.metaData.question, question)  // 리뷰 메타데이터에서 question과 조인
                .where(question.isDeleted.isFalse()
                        .and(review.isDeleted.isFalse())
                        .and(review.createdAt.goe(LocalDateTime.now().minusDays(7)))  // 최근 7일간 필터링
                )
                .limit(10)
                .orderBy(review.likeCount.desc())
                .fetch();
    }


    @Override
    @Description("사용자가 작성한 리뷰 중 좋아요 수가 많은 순으로 리뷰를 조회합니다.")
    public Page<ReviewWithReviewLikesByUserRdsDTO> getReviewsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable) {
        List<ReviewWithReviewLikesByUserRdsDTO> reviews = queryFactory.select(Projections.constructor(
                        ReviewWithReviewLikesByUserRdsDTO.class,
                        review.id,
                        Expressions.stringTemplate("function('LEFT', {0}, {1})", question.content, 20),
                        review.content,
                        review.likeCount,
                        review.likeCount.sum().as("totalReviewLikes")
                ))
                .from(review)
                .leftJoin(review.metaData.question, question)
                .where(review.userId.eq(userId)
                        .and(review.isDeleted.isFalse()))
                .groupBy(review.id, question.content, review.content, review.likeCount)
                .orderBy(review.likeCount.sum().desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = Optional.ofNullable(queryFactory.select(review.count())
                .from(review)
                .where(review.userId.eq(userId)
                        .and(review.isDeleted.isFalse()))
                .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(reviews, pageable, totalCount);
    }


    @Override
    @Description("사용자가 좋아요를 누른 리뷰를 조회합니다.")
    public Page<Review> findUserInterestedReviews(Long userId, Pageable pageable) {
        List<Review> reviews = queryFactory.select(review)
                .from(review)
                .leftJoin(reviewFavorite)
                .on(reviewFavorite.review.id.eq(review.id))
                .where(reviewFavorite.userId.eq(userId)
                        .and(review.isDeleted.isFalse()))
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = Optional.ofNullable(queryFactory.select(review.count())
                .leftJoin(reviewFavorite)
                .on(reviewFavorite.review.id.eq(review.id))
                .where(reviewFavorite.userId.eq(userId)
                        .and(review.isDeleted.isFalse()))
                .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(reviews, pageable, totalCount);
    }
}
