package com.company.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface BookingRepository {

    Booking getBookingById(long booking_id);
    List<Booking> getAll();
    Booking save(Booking booking);
    Booking update(long booking_id, long listing_booked, long user, double fee, Date start_date, Date end_date, int cancelled);
    void deleteById(long booking_id);
}
