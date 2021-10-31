package com.company.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {

    Booking getBookingById(Long booking_id);
    List<Booking> getBookingsByOwnerId(Long booking_id);
    List<Booking> getAll();
    Booking save(Booking booking);
    Booking update(Long booking_id, Long listing_id, Long user, double fee, LocalDate start_date, LocalDate end_date, boolean cancelled);
    void deleteById(Long booking_id);
}
