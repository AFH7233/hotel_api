package com.afh.choice.dao;

import com.afh.choice.dto.Amenity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AmenityDAO {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public AmenityDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void createAmenity(Amenity amenity) {
    jdbcTemplate.update("CALL create_amenity(?, ?)", amenity.getName(), amenity.getDescription());
  }

  public void updateAmenity(Amenity amenity) {
    jdbcTemplate.update(
        "CALL update_amenity(?, ?, ?)",
        amenity.getId(),
        amenity.getName(),
        amenity.getDescription());
  }

  public void deleteAmenity(long amenityId) {
    jdbcTemplate.update("CALL delete_amenity(?)", amenityId);
  }
}
