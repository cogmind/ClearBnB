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
        this.getFilteredListings();
        this.getListingsByUserId();
        this.getListingById();
    }

    private void getListingsByUserId() {
        app.get("/api/user-listings/:user-id", (req, res) -> {
            System.out.println("GET LISTINGS BY USER ID");
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            Long owner_id = Long.parseLong(req.params("user-id"));
            res.json(listingRepository.getListingsByOwnerId(owner_id));
        });
    }

    private void getListingById() {
        app.get("/api/listing/:id", (req, res) -> {
            System.out.println("GET LISTING BY ID");
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            Long id = Long.parseLong(req.params("id"));
            res.json(listingRepository.getListingById(id));
        });
    }

    private void getListings() {
        app.get("/api/listings", (req, res) -> {
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            res.json(listingRepository.getAll(false));
        });
    }

    private void getFilteredListings() {
        app.get("/api/search", (req, res) -> {
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            System.out.println("QUERIES: " + req.query());
            res.json(listingRepository.getFilteredListings(req.query()));
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
