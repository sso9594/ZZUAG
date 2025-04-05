package com.recycle.service.review.service;

import com.recycle.domain.review.dto.CachedReviewLikePage;
import com.recycle.domain.review.dto.CachedReviewLikesByUser;
import com.recycle.domain.review.dto.ReviewWithReviewLikesByUserRdsDTO;
import com.recycle.domain.review.dto.TopReviewedDTO;
import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.service.ReviewRdsQueryService;
import com.recycle.domain.review.service.ReviewRedisQueryService;
import com.recycle.service.review.dto.ReviewWithReviewLikesByUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class ReviewQueryDomainService {
    private final ReviewRdsQueryService reviewRdsQueryService;
    private final ReviewRedisQueryService reviewRedisQueryService;

    @Transactional(readOnly = true)
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRdsQueryService.getReviewById(reviewId);
    }

    @Transactional(readOnly = true)
    public Page<ReviewWithReviewLikesByUserDTO> getReviewsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable) {
        int page = pageable.getPageNumber();
        Optional<CachedReviewLikePage> cached = reviewRedisQueryService.getCachedReviewPage(userId, page);
        if(cached.isPresent()){
            CachedReviewLikePage cachedReviewLikePage = cached.get();
            List<CachedReviewLikesByUser> cachedReviews = cachedReviewLikePage.getContent();
            List<ReviewWithReviewLikesByUserDTO> reviews = cachedReviews.stream()
                    .map(cachedReview -> ReviewWithReviewLikesByUserDTO.builder()
                            .reviewId(cachedReview.reviewId())
                            .questionPreview(cachedReview.questionPreview())
                            .content(cachedReview.content())
                            .reviewLikeCnt(cachedReview.reviewLikeCnt())
                            .totalReviewLikes(cachedReview.totalReviewLikes())
                            .build()
                    ).toList();
            return new PageImpl<>(reviews, pageable, cachedReviewLikePage.getTotalElements());
        }

        Page<ReviewWithReviewLikesByUserRdsDTO> reviews = reviewRdsQueryService.getReviewsByUserIdAndTopLikeCountByPagination(userId, pageable);
        List<CachedReviewLikesByUser> toCache = reviews.getContent().stream()
                .map(review -> CachedReviewLikesByUser.builder()
                        .reviewId(review.reviewId())
                        .questionPreview(review.questionPreview())
                        .content(review.content())
                        .reviewLikeCnt(review.reviewLikeCnt())
                        .totalReviewLikes(review.totalReviewLikes())
                        .build()
                ).toList();
        CachedReviewLikePage cachedReviewLikePage = CachedReviewLikePage.builder()
                .content(toCache)
                .totalPages(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .currentPage(reviews.getNumber())
                .build();
        reviewRedisQueryService.setCachedReviewPage(userId, page, cachedReviewLikePage);

        return reviews.map(review -> ReviewWithReviewLikesByUserDTO.builder()
                .reviewId(review.reviewId())
                .questionPreview(review.questionPreview())
                .content(review.content())
                .reviewLikeCnt(review.reviewLikeCnt())
                .totalReviewLikes(review.totalReviewLikes())
                .build()
        );
    }

    @Transactional(readOnly = true)
    public List<TopReviewedDTO> getTopReviewedDTOs() {
        return reviewRdsQueryService.getTopReviewedDTOs();
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByQuestionId(Long questionId) {
        return reviewRdsQueryService.getReviewsByQuestionId(questionId);
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRdsQueryService.getReviewsByUserId(userId);
    }
}
