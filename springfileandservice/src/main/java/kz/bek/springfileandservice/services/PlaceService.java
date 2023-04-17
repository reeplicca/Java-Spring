package kz.bek.springfileandservice.services;

import kz.bek.springfileandservice.entities.Place;

import java.util.List;

public interface PlaceService {
    Place addPlace(Place place);
    List<Place> getAllPlaces();
    Place getPlace(Long id);
    Place updatePlace(Place place);
    void deletePlace(Long id);
}
