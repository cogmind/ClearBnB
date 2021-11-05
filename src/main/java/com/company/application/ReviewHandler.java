package com.company.application;

import com.company.domain.Review;
import com.company.infrastructure.ReviewRepositoryImpl;
import express.Express;
import jakarta.persistence.EntityManager;

public class ReviewHandler {
    private final Express app;
    private final ReviewRepositoryImpl reviewRepository;

    public ReviewHandler(Express app, EntityManager entityManager) {
        this.app = app;
        this.reviewRepository = new ReviewRepositoryImpl(entityManager);
        this.getReviewById();
        this.saveReview();
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
}
