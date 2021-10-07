package com.company.infrastructure;

import com.company.domain.Booking;
import com.company.domain.BookingRepository;
import com.company.domain.Listing;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    private final EntityManager entityManager;

    public BookingRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Booking getBookingById(Long booking_id) {
        return entityManager.createQuery("SELECT b FROM Booking b WHERE b.booking_id = :booking_id", Booking.class)
                .setParameter("booking_id", booking_id)
                .getSingleResult();
    }

    public List<Booking> getBookingsByListingId(Long listing_id) {
        return entityManager.createQuery("SELECT b FROM Booking b WHERE b.listing_id = :listing_id", Booking.class)
                .setParameter("listing_id", listing_id)
                .getResultList();
    }

    @Override
    public List<Booking> getAll() {
        return entityManager.createQuery("FROM Booking").getResultList();
    }

    @Override
    public Booking save(Booking booking) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(booking);
            entityManager.getTransaction().commit();
            return booking;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Booking update(Long booking_id, Long listing_booked, Long user, double fee, Date start_date, Date end_date, int cancelled) {
        Booking booking = this.getBookingById(booking_id);

        try {
            if (listing_booked > 0) {
                booking.setListing_booked(listing_booked);
            }
            if (user > 0) {
                booking.setUser(user);
            }
            if (fee > 0) {
                booking.setFee(fee);
            }
            if (start_date != null) {
                booking.setStart_date(start_date);
            }
            if (end_date != null) {
                booking.setEnd_date(end_date);
            }
            if (cancelled >= 0) {
                booking.setCancelled(cancelled);
            }
            return booking;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Long booking_id) {
        // Delete by versioning
        // Copy, set version number to plus one, increase ID by one and save
        Booking booking = this.getBookingById(booking_id);
        try {
            Booking clone = entityManager.find(Booking.class, booking.getBooking_id());
            entityManager.detach(clone);
            clone.setBooking_id(null);// TODO DEBUG Will this work?
            entityManager.persist(clone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
