package com.afh.choice.dao.interfaces;


import com.afh.choice.soap.Amenity;

public interface AmenityDAO {
  Amenity getAmenityByName(String amenityName);

}
