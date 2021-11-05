package com.company.application;

import com.company.domain.Review;
import com.company.infrastructure.ReviewRepositoryImpl;
import express.Express;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ReviewHandler {
    private final Express app;
    private final ReviewRepositoryImpl reviewRepository;

    public ReviewHandler(Express app, EntityManager entityManager) {
        this.app = app;
        this.reviewRepository = new ReviewRepositoryImpl(entityManager);
        this.getReviewById();
        this.getReviewByBookingId();
        this.saveReview();
        this.getAllReviews();
    }

    private void getAllReviews() {
        app.get("/api/reviews", (req, res) -> {
            System.out.println("GETTING REVIEWS");
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");

            res.json(this.reviewRepository.getAllReviews());
        });
    }

    private void saveReview() {
        app.post("/api/create-review", (req, res) -> {
            Review review = req.body(Review.class);
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            reviewRepository.save(review);
        });
    }

    private void getReviewById() {
        app.get("/api/review/:id", (req, res) -> {
            System.out.println("GETTING REVIEW");
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");

            Long id = Long.parseLong(req.params("id"));
            Review review = reviewRepository.getReviewById(id);
            System.out.println(review);
            res.send(review);
        });
    }


    private void getReviewByBookingId() {
        app.get("/api/booking-reviews/:id", (req, res) -> {
            System.out.println("GETTING REVIEWS BY BOOKING ID");
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");

            Long id = Long.parseLong(req.params("id"));
            List<Review> reviews = reviewRepository.getReviewsByBookingId(id);
            System.out.println(reviews);
            res.send(reviews);
        });
    }
}
