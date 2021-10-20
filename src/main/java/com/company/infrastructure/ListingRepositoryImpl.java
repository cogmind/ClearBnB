package com.company.infrastructure;

import com.company.domain.Listing;
import com.company.domain.ListingRepository;

import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ListingRepositoryImpl implements ListingRepository {

    private final EntityManager entityManager;

    public ListingRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Listing> getFilteredListings(){
        Session session = entityManager.unwrap(Session.class);
        Filter priceFilter = session.enableFilter("priceFilter");
        priceFilter.setParameter("priceComparison", 50000.0);

        //List<ChessPlayer> chessPlayersAfterEnable = em.createQuery("select p from ChessPlayer p", ChessPlayer.class)
        List<Listing> filteredListings = this.getAll();
        session.close();
        return filteredListings;
    }

    @Override
    public Listing getListingById(Long listing_id) {
        return entityManager.createQuery("SELECT l FROM Listing l WHERE l.listing_id = :listing_id", Listing.class)
                .setParameter("listing_id", listing_id)
                .getSingleResult();
    }

    @Override
    public List<Listing> getListingsByOwnerId(Long owner_id) {
        return entityManager.createQuery("SELECT l FROM Listing l WHERE l.owner_id = :owner_id", Listing.class)
                .setParameter("owner_id", owner_id)
                .getResultList();
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
    public Listing update(Long listing_id, int version, Long owner_id, Timestamp audited_datetime, String title, String description, String image_url, String location, int guests, double price, Date listing_start_date, Date listing_end_date) {
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
                listing.setStart(listing_start_date);
            }
            if (listing_end_date != null) {
                listing.setEnd(listing_end_date);
            }

            entityManager.getTransaction().commit();
            return listing;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Listing getListingByTitle(String title) {
        return entityManager.createQuery("SELECT l FROM Listing l WHERE l.title = :title", Listing.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public void deleteById(Long listing_id) {
        // Delete by versioning
        // Copy, set version number to plus one, increase ID by one and save
        Listing listing = this.getListingById(listing_id);
        try {
            Listing clone = entityManager.find(Listing.class, listing.getListing_id());
            entityManager.detach(clone);
            clone.setListing_id(null);// TODO DEBUG Will this work?
            entityManager.persist(clone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
