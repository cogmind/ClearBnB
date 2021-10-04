package com.company.domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

public class Listing {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long listing_id;
    private int version;
    private long owner_id;
    private Timestamp audited_datetime; //Debuggable type
    private String title;
    private String description;
    private String image_url;
    private String location;
    private int guests;
    private double price;
    private Date listing_start_date;
    private Date listing_end_date;

    public long getListing_id() {
        return listing_id;
    }

    public void setListing_id(long listing_id) {
        this.listing_id = listing_id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public Timestamp getAudited_datetime() {
        return audited_datetime;
    }

    public void setAudited_datetime(Timestamp audited_datetime) {
        this.audited_datetime = audited_datetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getListing_start_date() {
        return listing_start_date;
    }

    public void setListing_start_date(Date listing_start_date) {
        this.listing_start_date = listing_start_date;
    }

    public Date getListing_end_date() {
        return listing_end_date;
    }

    public void setListing_end_date(Date listing_end_date) {
        this.listing_end_date = listing_end_date;
    }
}
