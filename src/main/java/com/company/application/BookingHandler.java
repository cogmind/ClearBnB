package com.company.application;

import com.company.domain.Booking;
import com.company.domain.Listing;
import com.company.domain.User;
import com.company.infrastructure.BookingRepositoryImpl;
import com.company.infrastructure.ListingRepositoryImpl;
import com.company.infrastructure.UserRepositoryImpl;
import express.Express;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.util.List;

public class BookingHandler {
    Express app;
    EntityManager entityManager;
    BookingRepositoryImpl bookingRepository;
    double PROFIT_MARGIN = 0.15;

    public BookingHandler(Express app, EntityManager entityManager) {
        this.app = app;
        this.entityManager = entityManager;
        this.bookingRepository = new BookingRepositoryImpl(this.entityManager);
        this.createBooking();
        this.viewBookings();
        this.getBookingsByUserId();
    }

    private void getBookingsByUserId() {
        app.get("/api/user-bookings/:user-id", (req, res) -> {
            System.out.println("GET BOOKINGS BY USER ID");
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            Long owner_id = Long.parseLong(req.params("user-id"));
            res.json(bookingRepository.getBookingsByOwnerId(owner_id));
        });
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

                // Compute fee based on number of days of stay and price per night
                long days = (booking_end.getTime() - booking_start.getTime()) / (1000 * 60 * 60 * 24) + 1;
                System.out.println("Number of days: " + days);
                double fee = booking.getFee() * days;

                // Add profit margin
                fee = fee * (1.0 + PROFIT_MARGIN);
                booking.setFee(fee);

                // Check if funds are available
                if (fee > booking.getAvailable_funds()) {
                    System.out.println("NOT ENOUGH MONEY IN ACCOUNT");
                    res.json("Not enough money in account.");
                } else { // Funds are available

                    // Retrieve listing from DB
                    ListingRepositoryImpl listingRepository = new ListingRepositoryImpl(this.entityManager);
                    Listing current_listing = listingRepository.getListingById(booking.getListing_id());
                    Date available_start = current_listing.getStart_date();
                    Date available_end = current_listing.getEnd_date();

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
                    if ((booking_start.compareTo(available_start) >= 0) && (booking_end.compareTo(available_end) <= 0)) {
                        System.out.println("Valid booking (1). Valid dates when booking...");

                        // Check if dates have already been taken, for all bookings of a listing
                        BookingRepositoryImpl bookings = new BookingRepositoryImpl(entityManager);
                        List<Booking> bookingsForCurrentListing = bookings.getBookingsByListingId(current_listing.getListing_id());

                        Date next_start;
                        Date next_end;
                        for (Booking next_booking : bookingsForCurrentListing) {
                            next_start = next_booking.getStart_date();
                            next_end = next_booking.getEnd_date();
                            if (!(booking_start.compareTo(next_end) >= 0 || booking_end.compareTo(next_start) <= 0)) {
                                res.json("ERROR: Booking overlaps with an existing booking.");
                                return;
                            }
                        }

                        // Save booking
                        bookingRepository.save(booking);

                        // Update balance of account
                        UserRepositoryImpl userRepository = new UserRepositoryImpl(this.entityManager);
                        User current_user = userRepository.getById(booking.getUser());
                        current_user.setBalance(booking.getAvailable_funds() - fee);
                        userRepository.save(current_user);

                        res.json("Valid booking. No overlap with current bookings");
                        System.out.println("Valid booking. No overlap with current bookings");
                    } else {
                        System.out.println("Invalid dates when booking. Dates not available (1)");
                        res.json("ERROR: Invalid dates");
                    }
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                res.json("Internal ERROR in BookingHandler");
            }
        });
    }
}
