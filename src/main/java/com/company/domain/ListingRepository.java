package com.company.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface ListingRepository {

    Listing getListingById(long listing_id);
    Listing getListingByOwnerId(long owner_id);
    List<Listing> getAll();
    Listing save(Listing listing);
    Listing update(long listing_id, int version, long owner_id, Timestamp audited_datetime, String title, String description, String image_url, String location, int guests, double price, Date listing_start_date, Date listing_end_date);
    void deleteById(long listing_id);
}
