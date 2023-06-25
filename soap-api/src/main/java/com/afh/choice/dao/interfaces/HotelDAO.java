package com.afh.choice.dao.interfaces;

import com.afh.choice.soap.Hotel;
import com.afh.choice.soap.HotelComplete;

import java.util.List;

public interface HotelDAO {
  void createHotel(Hotel hotel);

  void updateHotel(Hotel hotel);

  void deleteHotelByName(String name);

  HotelComplete getHotelByName(String name);
  List<HotelComplete> searchHotelsByName(String query);

  void addAmenityToHotel(long hotelId, long amenityId);

  void removeAmenityFromHotel(long hotelId, long amenityId);
}
