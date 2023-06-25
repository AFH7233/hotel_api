package com.afh.choice.dao;

import com.afh.choice.dao.interfaces.HotelDAO;
import com.afh.choice.soap.Amenity;
import com.afh.choice.soap.AmenityList;
import com.afh.choice.soap.Hotel;
import com.afh.choice.soap.HotelComplete;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HotelDAOImpl implements HotelDAO {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public HotelDAOImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void createHotel(Hotel hotel) {
    jdbcTemplate.update(
        "CALL create_hotel(?, ?, ?)", hotel.getName(), hotel.getAddress(), hotel.getRating());
  }

  @Override
  public void updateHotel(Hotel hotel) {
    jdbcTemplate.update(
        "CALL update_hotel(?, ?, ?, ?)",
        hotel.getId(),
        hotel.getName(),
        hotel.getAddress(),
        hotel.getRating());
  }

  @Override
  public void deleteHotelByName(String hotelName) {
    jdbcTemplate.update("CALL delete_hotel_by_name(?)", hotelName);
  }

  @Override
  public HotelComplete getHotelByName(String hotelName) {
    HotelComplete hotelComplete =
        jdbcTemplate.queryForObject(
            "CALL get_hotel_by_name(?)",
            new Object[] {hotelName},
            (rs, rowNum) -> {
              HotelComplete hotel = new HotelComplete();
              hotel.setId(rs.getLong("id"));
              hotel.setName(rs.getString("name"));
              hotel.setAddress(rs.getString("address"));
              hotel.setRating(rs.getInt("rating"));
              return hotel;
            });

    AmenityList amenities = getAmenitiesByHotelId(hotelComplete.getId());
    hotelComplete.setAmenities(amenities);

    return hotelComplete;
  }

  @Override
  public List<HotelComplete> searchHotelsByName(String query) {
    List<HotelComplete> hotels =
        jdbcTemplate.query(
            "CALL search_hotels_by_name(?)",
            new Object[] {query},
            (rs, rowNum) -> {
              HotelComplete hotel = new HotelComplete();
              hotel.setId(rs.getLong("hotel_id"));
              hotel.setName(rs.getString("hotel_name"));
              hotel.setAddress(rs.getString("hotel_address"));
              hotel.setRating(rs.getInt("hotel_rating"));
              return hotel;
            });

    for (HotelComplete hotel : hotels) {
      AmenityList amenities = getAmenitiesByHotelId(hotel.getId());
      hotel.setAmenities(amenities);
    }

    return hotels;
  }

  @Override
  public void addAmenityToHotel(long hotelId, long amenityId) {
    jdbcTemplate.update("CALL add_amenity_to_hotel(?, ?)", hotelId, amenityId);
  }

  @Override
  public void removeAmenityFromHotel(long hotelId, long amenityId) {
    jdbcTemplate.update("CALL remove_amenity_from_hotel(?, ?)", hotelId, amenityId);
  }

  private AmenityList getAmenitiesByHotelId(long hotelId) {
    List<Amenity> amenities =
        jdbcTemplate
            .query(
                "CALL get_amenities_by_hotel(?)",
                new Object[] {hotelId},
                (rs, rowNum) -> {
                  Amenity amenity = new Amenity();
                  amenity.setId(rs.getLong("amenity_id"));
                  amenity.setName(rs.getString("amenity_name"));
                  amenity.setDescription(rs.getString("amenity_description"));
                  return amenity;
                })
            .stream()
            .collect(Collectors.toList());
    AmenityList amenityList = new AmenityList();
    amenityList.getAmenity().addAll(amenities);
    return amenityList;
  }
}
