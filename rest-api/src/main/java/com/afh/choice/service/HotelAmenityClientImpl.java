package com.afh.choice.service;

import com.afh.choice.service.interfaces.HotelAmenityClient;
import com.afh.choice.soap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class HotelAmenityClientImpl implements HotelAmenityClient {

  @Value("${hotel.endpoint.url}")
  private String NAMESPACE_URI;

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
  public SearchHotelsByNameResponse searchHotelsByName(String query, int pageNumber) {
    SearchHotelsByNameRequest request = new SearchHotelsByNameRequest();
    request.setQuery(query);
    request.setPageNumber(pageNumber);

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
  public RemoveAmenityFromHotelResponse removeAmenityFromHotel(String hotelName, String amenityName) {
    RemoveAmenityFromHotelRequest request = new RemoveAmenityFromHotelRequest();
    request.setHotelName(hotelName);
    request.setAmenityName(amenityName);

    RemoveAmenityFromHotelResponse response =
            (RemoveAmenityFromHotelResponse)
                    webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

    return response;
  }

}
