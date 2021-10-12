package com.company.application;

import com.company.domain.Booking;
import com.company.domain.Listing;
import com.company.infrastructure.BookingRepositoryImpl;
import com.company.infrastructure.ListingRepositoryImpl;
import express.Express;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.util.List;

public class BookingHandler {
    Express app;
    EntityManager entityManager;
    BookingRepositoryImpl bookingRepository;

    public BookingHandler(Express app, EntityManager entityManager) {
        this.app = app;
        this.entityManager = entityManager;
        this.bookingRepository = new BookingRepositoryImpl(this.entityManager);
        this.createBooking();
        this.viewBookings();
    }

    private void viewBookings() {
        app.get("/api/bookings", (req, res) -> {
            res.json(this.bookingRepository.getAll());
        });
    }

    private void createBooking() {
        app.post("/api/createbooking", (req, res) -> {
            Booking booking = req.body(Booking.class);
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");

            try {
                Date booking_start = booking.getStart_date();
                Date booking_end = booking.getEnd_date();

                // Retrieve listing from DB
                ListingRepositoryImpl listingRepository = new ListingRepositoryImpl(this.entityManager);
                Listing current_listing = listingRepository.getListingById(booking.getListing_booked());
                Date available_start = current_listing.getStart();
                Date available_end = current_listing.getEnd();

                // Check if dates are valid for a specific listing
                if ((booking_start.compareTo(available_start) > 0) && (booking_end.compareTo(available_end) <= 0)) {
                    System.out.println("Valid booking (1). Valid dates when booking...");
                } else {
                    System.out.println("Invalid dates when booking. Dates not available (1)");
                    res.json("ERROR: Invalid dates");
                }

                // Check if dates have already been taken, for all bookings of a listing
                BookingRepositoryImpl bookings = new BookingRepositoryImpl(entityManager);
                List<Booking> bookingsForCurrentListing = bookings.getBookingsByListingId(current_listing.getListing_id());

                Date next_start;
                Date next_end;
                for (Booking next_booking: bookingsForCurrentListing) {
                    next_start = next_booking.getStart_date();
                    next_end = next_booking.getEnd_date();
                    if (!(booking_start.compareTo(next_end) > 0 || booking_end.compareTo(next_start) > 0)) {
                        res.json("ERROR: Booking overlaps with an existing booking.");
                        return;
                    }
                }
                res.json("Valid booking. No overlap with current bookings");
                System.out.println("Valid booking. No overlap with current bookings");
            }
            catch(Exception e) {
                    res.json("Internal ERROR in BookingHandler");
            }
        });
    }
}
