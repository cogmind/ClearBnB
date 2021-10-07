package com.company.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface ListingRepository {

    Listing getListingById(Long listing_id);
    List<Listing> getListingsByOwnerId(Long owner_id);
    Listing getListingByTitle(String title);
    List<Listing> getAll();
    Listing save(Listing listing);
    Listing update(Long listing_id, int version, Long owner_id, Timestamp audited_datetime, String title, String description, String image_url, String location, int guests, double price, Date listing_start_date, Date listing_end_date);
    void deleteById(Long listing_id);
}
