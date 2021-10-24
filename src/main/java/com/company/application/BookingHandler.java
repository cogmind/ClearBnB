package com.company.application;

import com.company.domain.Booking;
import com.company.domain.Listing;
import com.company.infrastructure.BookingRepositoryImpl;
import com.company.infrastructure.ListingRepositoryImpl;
import express.Express;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.time.LocalDate;
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
                LocalDate booking_start = booking.getStart();
                LocalDate booking_end = booking.getEnd();

                // Retrieve listing from DB
                ListingRepositoryImpl listingRepository = new ListingRepositoryImpl(this.entityManager);
                Listing current_listing = listingRepository.getListingById(booking.getListing_id());
                LocalDate available_start = current_listing.getStart();
                LocalDate available_end = current_listing.getEnd();

                // Check if dates are valid for a specific listing
                System.out.println("----------------------------");
                System.out.println("available_start: " + available_start);
                System.out.println("booking_start: " + booking_start);
                System.out.println("----------------------------");
                System.out.println("available_end: " + available_end);
                System.out.println("booking_end: " + booking_end);
                System.out.println("----------------------------");
                System.out.println("booking_start.compareTo(available_start): " + booking_start.compareTo(available_start));
                System.out.println("booking_end.compareTo(available_end) <= 0: " + booking_end.compareTo(available_end));
                if ((booking_start.compareTo(available_start) < 0) && (booking_end.compareTo(available_end) < 0)) {
                    System.out.println("Valid booking (1). Valid dates when booking...");

                    // Check if dates have already been taken, for all bookings of a listing
                    BookingRepositoryImpl bookings = new BookingRepositoryImpl(entityManager);
                    List<Booking> bookingsForCurrentListing = bookings.getBookingsByListingId(current_listing.getListing_id());

                    LocalDate next_start;
                    LocalDate next_end;
                    for (Booking next_booking: bookingsForCurrentListing) {
                        next_start = next_booking.getStart();
                        next_end = next_booking.getEnd();
                        if (!(booking_start.compareTo(next_end) > 0 || booking_end.compareTo(next_start) > 0)) {
                            res.json("ERROR: Booking overlaps with an existing booking.");
                            return;
                        }
                    }
                    bookingRepository.save(booking);
                    res.json("Valid booking. No overlap with current bookings");
                    System.out.println("Valid booking. No overlap with current bookings");
                } else {
                    System.out.println("Invalid dates when booking. Dates not available (1)");
                    res.json("ERROR: Invalid dates");
                }

            }
            catch(Exception e) {
                e.printStackTrace();
                res.json("Internal ERROR in BookingHandler");
            }
        });
    }
}
