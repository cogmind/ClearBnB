package com.company.infrastructure;

import com.company.domain.Listing;
import com.company.domain.Review;
import com.company.domain.ReviewRepository;
import jakarta.persistence.EntityManager;

public class ReviewRepositoryImpl implements ReviewRepository {

    private final EntityManager entityManager;

    public ReviewRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Review getReviewById(Long review_id) {
        return entityManager.createQuery("SELECT r FROM Review r WHERE r.review_id = :review_id", Review.class)
                .setParameter("review_id", review_id)
                .getSingleResult();
    }

    @Override
    public Review save(Review review) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(review);
            entityManager.getTransaction().commit();
            return review;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
