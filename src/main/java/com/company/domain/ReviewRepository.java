package com.company.domain;

import java.util.List;

public interface ReviewRepository {
    Review getReviewById(Long review_id);
    List<Review> getReviewsByBookingId(Long booking_id);
    Review save(Review review);
    List<Review> getAllReviews();
}
