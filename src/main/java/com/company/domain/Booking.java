package com.company.domain;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long booking_id;
    Long listing_booked;
    Long user;
    double fee;
    Date start_date;
    Date end_date;
    boolean cancelled;

    public long getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    public long getListing_booked() {
        return listing_booked;
    }

    public void setListing_booked(Long listing_booked) {
        this.listing_booked = listing_booked;
    }

    public long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
