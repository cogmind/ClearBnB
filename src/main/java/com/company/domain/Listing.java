package com.company.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="listing")
@FilterDef(name="priceFilter", parameters=@ParamDef(name="priceComparison",type="double"))
@Filters({
        @Filter(name="priceFilter", condition="price <= :priceComparison")
})
public class Listing implements Cloneable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long listing_id;
    private int version;
    private Long owner_id;
    private Timestamp audited_datetime; //Debuggable type
    private String title;
    private String description;
    private String image_url;
    private String location;
    private int guests;
    private double price;
    private Date start;
    private Date end;

    public Object clone() {
        Listing listing = new Listing();
        listing.setVersion(listing.getVersion() + 1);

        return listing;
    }

    public long getListing_id() {
        return listing_id;
    }

    public void setListing_id(Long listing_id) {
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
