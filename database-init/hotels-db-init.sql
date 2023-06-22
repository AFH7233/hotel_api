-- Create the `hotels` table
CREATE TABLE IF NOT EXISTS Hotels (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    rating TINYINT CHECK(rating >= 0 AND rating <= 5),
    amenityId INT
);

-- Create the `amenities` table
CREATE TABLE IF NOT EXISTS Amenities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

-- Add a foreign key in `Hotels` to reference the `Amenities` table
ALTER TABLE Hotels
    ADD CONSTRAINT fk_amenity
    FOREIGN KEY (amenityId) REFERENCES Amenities(id);

-- Procedure to create a hotel
DELIMITER $$

CREATE PROCEDURE create_hotel(IN hotel_name VARCHAR(255), IN hotel_address VARCHAR(255), IN hotel_rating TINYINT, IN hotel_amenity_id INT)
BEGIN
    INSERT INTO Hotels (name, address, rating, amenityId)
    VALUES (hotel_name, hotel_address, hotel_rating, hotel_amenity_id);
END$$

-- Procedure to create an amenity
CREATE PROCEDURE create_amenity(IN amenity_name VARCHAR(255), IN amenity_description VARCHAR(255))
BEGIN
    INSERT INTO Amenities (name, description)
    VALUES (amenity_name, amenity_description);
END$$

-- Procedure to delete a hotel
CREATE PROCEDURE delete_hotel(IN hotel_id INT)
BEGIN
    DELETE FROM Hotels WHERE id = hotel_id;
END$$

-- Procedure to update a hotel
CREATE PROCEDURE update_hotel(IN hotel_id INT, IN hotel_name VARCHAR(255), IN hotel_address VARCHAR(255), IN hotel_rating TINYINT, IN hotel_amenity_id INT)
BEGIN
    UPDATE Hotels SET
        name = hotel_name,
        address = hotel_address,
        rating = hotel_rating,
        amenityId = hotel_amenity_id
    WHERE id = hotel_id;
END$$

-- Procedure to get a hotel by id
CREATE PROCEDURE get_hotel_by_id(IN hotel_id INT)
BEGIN
    SELECT * FROM Hotels WHERE id = hotel_id;
END$$

-- Procedure to get an amenity by id
CREATE PROCEDURE get_amenity_by_id(IN amenity_id INT)
BEGIN
    SELECT * FROM Amenities WHERE id = amenity_id;
END$$

-- Procedure to get all hotels with a name like the given query
CREATE PROCEDURE get_hotels_by_name(IN search_query VARCHAR(255))
BEGIN
    SELECT * FROM Hotels WHERE name LIKE CONCAT('%', search_query, '%');
END$$
