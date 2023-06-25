package com.afh.choice.dao.interfaces;


import com.afh.choice.soap.Amenity;

public interface AmenityDAO {
  void createAmenity(Amenity amenity);

  void updateAmenity(Amenity amenity);

  void deleteAmenityByName(String amenityName);

  Amenity getAmenityByName(String amenityName);

}
