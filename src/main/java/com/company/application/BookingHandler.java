package com.company.application;

import com.company.domain.Booking;
import com.company.domain.Listing;
import com.company.domain.User;
import com.company.infrastructure.BookingRepositoryImpl;
import com.company.infrastructure.UserRepositoryImpl;
import express.Express;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

public class BookingHandler {
    Express app;
    EntityManager entityManager;
    BookingRepositoryImpl bookingRepository;

    public BookingHandler(Express app, EntityManager entityManager) {
        this.app = app;
        this.entityManager = entityManager;
        this.bookingRepository = new BookingRepositoryImpl(this.entityManager);
        this.createBooking();
    }

    private void createBooking() {
        app.post("/api/createbooking", (req, res) -> {
            Booking booking = req.body(Booking.class);
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            try{
                // Check if dates are taken for a specific listing
                // PSEUDO
                // get listing
                // Create range from start date and end date
                // For all chosen dates check if date is in range
                long current_listing = booking.getListing_booked();
                Date start = booking.getStart_date();
                Date end = booking.getEnd_date();
                // TODO No idea what function to use yet
                start.compareTo(end);
                );// Check which function to user
            }
            catch(Exception e){
                if(e.getMessage().equals("No entity found for query")){
                    res.json("Made a new booking.");
        });
    }
}
