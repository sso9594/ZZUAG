package com.recycle.domain.review.repository;

import com.recycle.domain.review.document.Review;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ReviewRepository extends ElasticsearchRepository<Review, Long> {
    // Custom query methods can be defined here
}
