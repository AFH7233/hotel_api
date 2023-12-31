-- Create the `hotels` table
CREATE TABLE IF NOT EXISTS Hotels (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    rating TINYINT CHECK(rating >= 0 AND rating <= 5)
);

-- Create the `amenities` table
CREATE TABLE IF NOT EXISTS Amenities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255)
);

-- Create the `hotel_amenities` joining table
CREATE TABLE IF NOT EXISTS HotelAmenities (
                                               hotel_id INT,
                                               amenity_id INT,
                                               PRIMARY KEY (hotel_id, amenity_id),
    FOREIGN KEY (hotel_id) REFERENCES Hotels(id) ON DELETE CASCADE,
    FOREIGN KEY (amenity_id) REFERENCES Amenities(id) ON DELETE CASCADE
    );

-- Triggers

DELIMITER $$
CREATE TRIGGER normalize_name_amenity_before_insert
    BEFORE INSERT ON Amenities
    FOR EACH ROW
BEGIN
    SET NEW.name = LOWER(TRIM(NEW.name));
END$$

CREATE TRIGGER normalize_name_amenity_before_update
    BEFORE UPDATE ON Amenities
    FOR EACH ROW
BEGIN
    SET NEW.name = LOWER(TRIM(NEW.name));
END$$

CREATE TRIGGER normalize_name_hotel_before_insert
    BEFORE INSERT ON Hotels
    FOR EACH ROW
BEGIN
    SET NEW.name = LOWER(TRIM(NEW.name));
END$$

CREATE TRIGGER normalize_name_hotel_before_update
    BEFORE UPDATE ON Hotels
    FOR EACH ROW
BEGIN
    SET NEW.name = LOWER(TRIM(NEW.name));
END$$

-- Procedure to create a hotel
DELIMITER $$

CREATE PROCEDURE create_hotel(IN hotel_name VARCHAR(255), IN hotel_address VARCHAR(255), IN hotel_rating TINYINT)
BEGIN
    INSERT INTO Hotels (name, address, rating)
    VALUES (hotel_name, hotel_address, hotel_rating);
END$$

-- Procedure to delete a hotel
CREATE PROCEDURE delete_hotel_by_name(IN hotel_name VARCHAR(255))
BEGIN
    DELETE FROM Hotels WHERE name = LOWER(TRIM(hotel_name));
END$$

-- Procedure to update a hotel
CREATE PROCEDURE update_hotel(IN hotel_id INT, IN hotel_name VARCHAR(255), IN hotel_address VARCHAR(255), IN hotel_rating TINYINT)
BEGIN
    UPDATE Hotels SET
        name = hotel_name,
        address = hotel_address,
        rating = hotel_rating
    WHERE id = hotel_id;
END$$

-- Procedure to get a hotel by id
CREATE PROCEDURE get_hotel_by_name(IN hotel_name VARCHAR(255))
BEGIN
    SELECT * FROM Hotels WHERE name = LOWER(TRIM(hotel_name));
END$$


-- Procedure to get all hotels with a name like the given query
CREATE PROCEDURE search_hotels_by_name(IN search_query VARCHAR(255), IN page_size INT, IN page_number INT)
BEGIN
    DECLARE offset INT;

    SET offset = (page_number - 1) * page_size;

    SELECT *
    FROM Hotels
    WHERE name LIKE CONCAT(search_query, '%')
    ORDER BY id
    LIMIT offset, page_size;
END$$

-- Procedure to get an amenity by name
CREATE PROCEDURE get_amenity_by_by_name(IN amenity_name VARCHAR(255))
BEGIN
SELECT * FROM Amenities WHERE name LIKE LOWER(TRIM(amenity_name));
END$$

-- Procedure to get amenities by hotel ID
CREATE PROCEDURE get_amenities_by_hotel(IN p_hotel_id INT)
BEGIN
SELECT
    Amenities.id AS amenity_id,
    Amenities.name AS amenity_name,
    Amenities.description AS amenity_description
FROM
    Amenities
        JOIN
    HotelAmenities ON Amenities.id = HotelAmenities.amenity_id
WHERE
        HotelAmenities.hotel_id = p_hotel_id;
END$$

-- Procedure to add an amenity to a hotel
CREATE PROCEDURE add_amenity_to_hotel(IN hotel_id INT, IN amenity_id INT)
BEGIN
INSERT INTO HotelAmenities (hotel_id, amenity_id)
VALUES (hotel_id, amenity_id);
END$$

-- Procedure to remove an amenity from a hotel
CREATE PROCEDURE remove_amenity_from_hotel(IN hotel_id INT, IN amenity_id INT)
BEGIN
DELETE FROM HotelAmenities
WHERE hotel_id = hotel_id
  AND amenity_id = amenity_id;
END$$

-- Insert amenities data
INSERT INTO Amenities (name, description) VALUES ('pool', '');
INSERT INTO Amenities (name, description) VALUES ('bar', '');
INSERT INTO Amenities (name, description) VALUES ('meeting room', 'meeting room for business travel, has screens and printer');
INSERT INTO Amenities (name, description) VALUES ('restaurant', '');