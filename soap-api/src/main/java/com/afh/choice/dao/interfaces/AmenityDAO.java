package com.afh.choice.dao.interfaces;


import com.afh.choice.soap.Amenity;

/**
 * Data access layer for Amenities CRUD.
 *
 * @author Andres Fuentes Hernandez
 */
public interface AmenityDAO {
    Amenity getAmenityByName(String amenityName);

}
