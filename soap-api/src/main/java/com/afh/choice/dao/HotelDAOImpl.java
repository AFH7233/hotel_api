package com.afh.choice.dao;

import com.afh.choice.dao.interfaces.HotelDAO;
import com.afh.choice.dto.Amenity;
import com.afh.choice.dto.Hotel;
import java.util.List;
import java.util.Set;
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
  public void deleteHotel(long hotelId) {
    jdbcTemplate.update("CALL delete_hotel(?)", hotelId);
  }

  @Override
  public Hotel getHotelById(long hotelId) {
    Hotel hotelComplete =
        jdbcTemplate.queryForObject(
            "CALL get_hotel_by_id(?)",
            new Object[] {hotelId},
            (rs, rowNum) -> {
              Hotel hotel = new Hotel();
              hotel.setId(rs.getLong("hotel_id"));
              hotel.setName(rs.getString("hotel_name"));
              hotel.setAddress(rs.getString("hotel_address"));
              hotel.setRating(rs.getInt("hotel_rating"));
              return hotel;
            });

    Set<Amenity> amenities = getAmenitiesByHotelId(hotelId);
    hotelComplete.setAmenities(amenities);

    return hotelComplete;
  }

  @Override
  public List<Hotel> getHotelsByName(String query) {
    List<Hotel> hotels =
        jdbcTemplate.query(
            "CALL get_hotels_by_name(?)",
            new Object[] {query},
            (rs, rowNum) -> {
              Hotel hotel = new Hotel();
              hotel.setId(rs.getLong("hotel_id"));
              hotel.setName(rs.getString("hotel_name"));
              hotel.setAddress(rs.getString("hotel_address"));
              hotel.setRating(rs.getInt("hotel_rating"));
              return hotel;
            });

    for (Hotel hotel : hotels) {
      Set<Amenity> amenities = getAmenitiesByHotelId(hotel.getId());
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

  private Set<Amenity> getAmenitiesByHotelId(long hotelId) {
    return jdbcTemplate
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
        .collect(Collectors.toSet());
  }
}
