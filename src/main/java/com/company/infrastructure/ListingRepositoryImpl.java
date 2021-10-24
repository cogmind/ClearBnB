package com.company.infrastructure;

import com.company.domain.Listing;
import com.company.domain.ListingRepository;

import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ListingRepositoryImpl implements ListingRepository {

    private final EntityManager entityManager;
    private Session session;

    public ListingRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Listing> getFilteredListings(Map<String, List<String>> queries) {
        session = entityManager.unwrap(Session.class);

        if (!((queries.get("price").get(0).equals("") || queries.get("price").get(0).equals("undefined")))) {
            System.out.println("FILTERING PRICE");
            Filter priceFilter = session.enableFilter("priceFilter");
            System.out.println("price: " + queries.get("price").get(0));

            double maxPrice = Double.parseDouble(queries.get("price").get(0));
            priceFilter.setParameter("priceComparison", maxPrice);
        } else {
            session.disableFilter("priceFilter");
        }

        if (!((queries.get("checkInDate").get(0).equals("") || queries.get("checkInDate").get(0).equals("undefined")))) {
            Filter afterDateFilter = session.enableFilter("afterDateFilter");

            String afterDate = queries.get("checkInDate").get(0);
            LocalDate afterDateLocal = LocalDate.parse(afterDate);
            System.out.println("After date: " + afterDateLocal);
            afterDateFilter.setParameter("afterDateLocal", afterDateLocal);
        } else {
            session.disableFilter("afterDateFilter");
        }

        if (!((queries.get("checkOutDate").get(0).equals("") || queries.get("checkOutDate").get(0).equals("undefined")))) {
            Filter beforeDateFilter = session.enableFilter("beforeDateFilter");

            String beforeDate = queries.get("checkOutDate").get(0);
            LocalDate beforeDateLocal = LocalDate.parse(beforeDate);
            System.out.println("Before date: " + beforeDateLocal);
            beforeDateFilter.setParameter("beforeDateLocal", beforeDateLocal);
        } else {
            session.disableFilter("beforeDateFilter");
        }

        //List<ChessPlayer> chessPlayersAfterEnable = em.createQuery("select p from ChessPlayer p", ChessPlayer.class)
        List<Listing> filteredListings = this.getAll(true);
        //session.close();
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
    public List<Listing> getAll(boolean filter) {

        if (!filter && session != null) {
            try {
                session.disableFilter("priceFilter");
                session.disableFilter("beforeDateFilter");
                session.disableFilter("afterDateFilter");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    public Listing update(Long listing_id, int version, Long owner_id, Timestamp audited_datetime, String title, String description, String image_url, String location, int guests, double price, LocalDate listing_start_date, LocalDate listing_end_date) {
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
