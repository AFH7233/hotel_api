package com.afh.choice.dao.interfaces;

import com.afh.choice.dto.Hotel;
import java.util.List;

public interface HotelDAO {
  void createHotel(Hotel hotel);

  void updateHotel(Hotel hotel);

  void deleteHotel(long hotelId);

  Hotel getHotelById(long hotelId);

  List<Hotel> getHotelsByName(String query);

  void addAmenityToHotel(long hotelId, long amenityId);

  void removeAmenityFromHotel(long hotelId, long amenityId);
}
