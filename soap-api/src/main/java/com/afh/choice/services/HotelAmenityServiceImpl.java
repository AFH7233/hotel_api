package com.afh.choice.services;

import com.afh.choice.dao.interfaces.AmenityDAO;
import com.afh.choice.dao.interfaces.HotelDAO;
import com.afh.choice.services.interfaces.HotelAmenityService;
import com.afh.choice.soap.Amenity;
import com.afh.choice.soap.AmenityList;
import com.afh.choice.soap.Hotel;
import java.util.List;
import java.util.Objects;

import com.afh.choice.soap.HotelComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelAmenityServiceImpl implements HotelAmenityService {

  private final HotelDAO hotelDAO;
  private final AmenityDAO amenityDAO;

  public HotelAmenityServiceImpl(@Autowired HotelDAO hotelDAO, @Autowired AmenityDAO amenityDAO) {
    this.hotelDAO = hotelDAO;
    this.amenityDAO = amenityDAO;
  }

  @Override
  public void createHotel(Hotel hotel) {
    this.hotelDAO.createHotel(hotel);
  }

  @Override
  public List<HotelComplete> searchHotelsByName(String query) {
    return this.hotelDAO.searchHotelsByName(query);
  }

  @Override
  public void updateHotel(Hotel hotel) {
    this.hotelDAO.updateHotel(hotel);
  }

  @Override
  public void deleteHotel(String name) {
    this.hotelDAO.deleteHotelByName(name);
  }

  @Override
  public void createAmenity(Amenity amenity) {
    this.amenityDAO.createAmenity(amenity);
  }

  @Override
  public void updateAmenity(Amenity amenity) {
    this.amenityDAO.updateAmenity(amenity);
  }

  @Override
  public void deleteAmenity(String amenityName) {
    this.amenityDAO.deleteAmenityByName(amenityName);
  }

  @Override
  public void addAmenityToHotel(String hotelName, String amenityName) {
    HotelComplete hotel = this.hotelDAO.getHotelByName(hotelName);
    Amenity amenity = this.amenityDAO.getAmenityByName(amenityName);
    this.hotelDAO.addAmenityToHotel(hotel.getId(), amenity.getId());
  }

  @Override
  public void removeAmenityToHotel(String hotelName, String amenityName) {
    HotelComplete hotel = this.hotelDAO.getHotelByName(hotelName);
    Amenity amenity = this.amenityDAO.getAmenityByName(amenityName);
    this.hotelDAO.removeAmenityFromHotel(hotel.getId(), amenity.getId());
  }
}
