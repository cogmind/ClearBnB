package com.company.domain;

public interface ReviewRepository {
    Review getReviewById(Long review_id);
    Review save(Review review);
}
