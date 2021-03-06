package com.company.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ListingRepository {

    List<Listing> getFilteredListings(Map<String, List<String>> queries) throws ParseException;
    Listing getListingById(Long listing_id);
    List<Listing> getListingsByOwnerId(Long owner_id);
    Listing getListingByTitle(String title);
    List<Listing> getAll(boolean filter);
    Listing save(Listing listing);
    Listing update(Long listing_id, int version, Long owner_id, Timestamp audited_datetime, String title, String description, String image_url, String location, int guests, double price, Date start, Date end);
    void deleteById(Long listing_id);
}
