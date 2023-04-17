package kz.bek.springfileandservice.services.impl;

import kz.bek.springfileandservice.entities.Place;
import kz.bek.springfileandservice.repositories.PlaceRepository;
import kz.bek.springfileandservice.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Place addPlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public Place getPlace(Long id) {
        return placeRepository.findAllById(id);
    }

    @Override
    public Place updatePlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }
}
