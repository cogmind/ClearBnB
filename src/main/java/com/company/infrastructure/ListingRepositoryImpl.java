package com.company.infrastructure;

import com.company.domain.Listing;
import com.company.domain.ListingRepository;
import com.company.domain.User;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ListingRepositoryImpl implements ListingRepository {

    private final EntityManager entityManager;

    public ListingRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Listing getListingById(long listing_id) {
        return entityManager.createQuery("SELECT l FROM Listing l WHERE l.listing_id = :listing_id", Listing.class)
                .setParameter("listing_id", listing_id)
                .getSingleResult();
    }

    @Override
    public Listing getListingByOwnerId(long owner_id) {
        return entityManager.createQuery("SELECT l FROM Listing l WHERE l.owner_id = :owner_id", Listing.class)
                .setParameter("owner_id", owner_id)
                .getSingleResult();
    }

    @Override
    public List<Listing> getAll() {
        return entityManager.createQuery("FROM Listing").getResultList();
    }

    @Override
    public Listing save(Listing listing) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(listing);
            entityManager.getTransaction().commit();
            return listing;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Listing update(long listing_id, int version, long owner_id, Timestamp audited_datetime, String title, String description, String image_url, String location, int guests, double price, Date listing_start_date, Date listing_end_date) {
        Listing listing = this.getListingById(listing_id);
        try {
            entityManager.getTransaction().begin();
            if (version > 0) {
                listing.setVersion(version);
            }
            if (audited_datetime != null) {
                listing.setAudited_datetime(audited_datetime);
            }
            if (title != null) {
                listing.setTitle(title);
            }
            if (description != null) {
                listing.setDescription(description);
            }
            if (image_url != null) {
                listing.setLocation(location);
            }
            if (guests > 0) {
                listing.setGuests(guests);
            }
            if (price > 0) {
                listing.setPrice(price);
            }
            if (listing_start_date != null) {
                listing.setListing_start_date(listing_start_date);
            }
            if (listing_end_date != null) {
                listing.setListing_end_date(listing_end_date);
            }

            entityManager.getTransaction().commit();
            return listing;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(long listing_id) {
        // Delete by versioning
        // Copy, set version number to plus one, increase ID by one and save
        Listing listing = this.getListingById(listing_id);
        Listing copyOfListing = listing; // TODO DEBUG: Should be copy by value not by reference
        try {
            entityManager.getTransaction().begin();
            copyOfListing.setVersion(copyOfListing.getVersion() + 1);
            copyOfListing.setListing_id(getAll().size() + 1); // DEBUGGABLE Does this work?
            save(copyOfListing);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
