package com.company.domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long booking_id;
    Long listing_id;
    Long user;
    double fee;
    LocalDate start;
    LocalDate end;
    int guests;
    boolean cancelled;

    public long getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    public long getListing_id() {
        return listing_id;
    }

    public void setListing_id(Long listing_id) {
        this.listing_id = listing_id;
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }


    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
