package com.company.application;

import com.company.domain.Listing;
import com.company.infrastructure.ListingRepositoryImpl;

import express.Express;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ListingHandler {
    Express app;
    EntityManager entityManager;
    ListingRepositoryImpl listingRepository;

    public ListingHandler(Express app, EntityManager entityManager) {
        this.app = app;
        this.entityManager = entityManager;
        this.listingRepository = new ListingRepositoryImpl(this.entityManager);
        this.createListing();
        this.getListings();
    }

    private void getListings() {
        app.get("/api/listings", (req, res) -> {
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            res.json(listingRepository.getAll());
        });
    }

    private void createListing() {
        app.post("/api/createlisting", (req, res) -> {
            Listing listing = req.body(Listing.class);
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            //res.append("Access-Control-Allow-Methods", "DELETE, POST, GET, OPTIONS");
            res.append("Access-Control-Allow-Credentials", "true");

            ListingRepositoryImpl listingRepository = new ListingRepositoryImpl(entityManager);
            // Check if title is unique
            try {
                Listing titleExists = listingRepository.getListingByTitle(listing.getTitle());
                res.sendStatus(400); // Bad request
                res.json("A listing with that title already exists");
            } catch(Exception e) {
                res.json("Title available, saving...");
                listingRepository.save(listing);
            }
        });
    }
}
