package com.afh.choice.dao.interfaces;

import com.afh.choice.soap.Hotel;
import com.afh.choice.soap.HotelComplete;

import java.util.List;

/**
 * Data access layer for Hotel CRUD.
 *
 * @author Andres Fuentes Hernandez
 */
public interface HotelDAO {
  void createHotel(Hotel hotel);

  void updateHotel(Hotel hotel);

  void deleteHotelByName(String name);

  HotelComplete getHotelByName(String name);
  List<HotelComplete> searchHotelsByName(String query, int pageNumber) ;

  void addAmenityToHotel(long hotelId, long amenityId);

  void removeAmenityFromHotel(long hotelId, long amenityId);
}
