package com.afh.choice.dto;

public class Amenity {
    private long id;
    private String name;
    private String description;

    public long getId(){
        return this.id;
    }

    public void setId(long amenityId) {
        this.id = amenityId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String amenityName) {
        this.name = amenityName;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String amenityDescription) {
        this.description = amenityDescription;
    }
}
