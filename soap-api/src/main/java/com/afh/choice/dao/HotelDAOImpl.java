package com.afh.choice.dao;

import com.afh.choice.dao.interfaces.HotelDAO;
import com.afh.choice.soap.Amenity;
import com.afh.choice.soap.AmenityList;
import com.afh.choice.soap.Hotel;
import com.afh.choice.soap.HotelComplete;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HotelDAOImpl implements HotelDAO {

  private final JdbcTemplate jdbcTemplate;

  @Value("${hotels.page.size:2}")
  private int PAGE_SIZE;

  @Autowired
  public HotelDAOImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  @Transactional
  public void createHotel(Hotel hotel) {
    jdbcTemplate.update(
        "CALL create_hotel(?, ?, ?)", hotel.getName(), hotel.getAddress(), hotel.getRating());
  }

  @Override
  @Transactional
  public void updateHotel(Hotel hotel) {
    jdbcTemplate.update(
        "CALL update_hotel(?, ?, ?, ?)",
        hotel.getId(),
        hotel.getName(),
        hotel.getAddress(),
        hotel.getRating());
  }

  @Override
  @Transactional
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
  public List<HotelComplete> searchHotelsByName(String query, int pageNumber) {
    List<HotelComplete> hotels =
        jdbcTemplate.query(
            "CALL search_hotels_by_name(?,?,?)",
            new Object[] {query, new Integer(PAGE_SIZE), new Integer(pageNumber)},
            (rs, rowNum) -> {
              HotelComplete hotel = new HotelComplete();
              hotel.setId(rs.getLong("id"));
              hotel.setName(rs.getString("name"));
              hotel.setAddress(rs.getString("address"));
              hotel.setRating(rs.getInt("rating"));
              return hotel;
            });

    for (HotelComplete hotel : hotels) {
      AmenityList amenities = getAmenitiesByHotelId(hotel.getId());
      hotel.setAmenities(amenities);
    }

    return hotels;
  }

  @Override
  @Transactional
  public void addAmenityToHotel(long hotelId, long amenityId) {
    jdbcTemplate.update("CALL add_amenity_to_hotel(?, ?)", hotelId, amenityId);
  }

  @Override
  @Transactional
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
