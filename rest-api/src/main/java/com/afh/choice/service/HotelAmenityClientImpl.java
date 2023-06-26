package com.afh.choice.service;

import com.afh.choice.service.interfaces.HotelAmenityClient;
import com.afh.choice.soap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class HotelAmenityClientImpl implements HotelAmenityClient {

  private String NAMESPACE_URI ="http://afh.com/choice/soap";

  private final WebServiceTemplate webServiceTemplate;

  public HotelAmenityClientImpl(@Autowired WebServiceTemplate webServiceTemplate) {
    this.webServiceTemplate = webServiceTemplate;
  }

  @Override
  public CreateHotelResponse createHotel(Hotel hotel) {
    CreateHotelRequest request = new CreateHotelRequest();
    request.setHotel(hotel);

    CreateHotelResponse response =
        (CreateHotelResponse) webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

    return response;
  }

  @Override
  public UpdateHotelResponse updateHotel(Hotel hotel) {
    UpdateHotelRequest request = new UpdateHotelRequest();
    request.setHotel(hotel);

    UpdateHotelResponse response =
        (UpdateHotelResponse) webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

    return response;
  }

  @Override
  public DeleteHotelResponse deleteHotel(String hotelName) {
    DeleteHotelRequest request = new DeleteHotelRequest();
    request.setHotelName(hotelName);

    DeleteHotelResponse response =
        (DeleteHotelResponse) webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

    return response;
  }

  @Override
  public SearchHotelsByNameResponse searchHotelsByName(String query) {
    SearchHotelsByNameRequest request = new SearchHotelsByNameRequest();
    request.setQuery(query);

    SearchHotelsByNameResponse response =
        (SearchHotelsByNameResponse)
            webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

    return response;
  }

  @Override
  public AddAmenityToHotelResponse addAmenityToHotel(String hotelName, String amenityName) {
    AddAmenityToHotelRequest request = new AddAmenityToHotelRequest();
    request.setHotelName(hotelName);
    request.setAmenityName(amenityName);

    AddAmenityToHotelResponse response =
        (AddAmenityToHotelResponse)
            webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

    return response;
  }

  @Override
  public UpdateAmenityResponse updateAmenity(Amenity amenity) {
    UpdateAmenityRequest request = new UpdateAmenityRequest();
    request.setAmenity(amenity);

    UpdateAmenityResponse response =
        (UpdateAmenityResponse) webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

    return response;
  }

  @Override
  public DeleteAmenityResponse deleteAmenity(String amenityName) {
    DeleteAmenityRequest request = new DeleteAmenityRequest();
    request.setAmenityName(amenityName);

    DeleteAmenityResponse response =
        (DeleteAmenityResponse) webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

    return response;
  }
}
