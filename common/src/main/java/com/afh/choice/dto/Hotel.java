package com.afh.choice.dto;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class Hotel {
    private long id;
    private String name;
    private String address;

    private int rating;

    private Set<Amenity> amenities;

    public void setId(long hotelId) {
        this.id = hotelId;
    }

    public void setName(String hotelName) {
        this.name = hotelName;
    }

    public void setAddress(String hotelAddress) {
        this.address = address;
    }

    public void setRating(int hotelRating) {
        this.rating = hotelRating;
    }


    public void setAmenities(Set<Amenity> amenities) {
        this.amenities = new TreeSet<>(amenities);
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }


    public int getRating() {
        return this.rating;
    }

}
