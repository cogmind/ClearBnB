package com.company.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface BookingRepository {

    Booking getBookingById(Long booking_id);
    List<Booking> getAll();
    Booking save(Booking booking);
    Booking update(Long booking_id, Long listing_booked, Long user, double fee, Date start_date, Date end_date, int cancelled);
    void deleteById(Long booking_id);
}
