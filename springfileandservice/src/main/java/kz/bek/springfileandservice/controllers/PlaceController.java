package kz.bek.springfileandservice.controllers;

import kz.bek.springfileandservice.entities.Place;
import kz.bek.springfileandservice.services.FileService;
import kz.bek.springfileandservice.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/add-place")
    public String openAddPlace() {
        return "add-place";
    }

    @PostMapping(value = "/add-place")
    public String addPlace(@RequestParam(name = "place-name") String name,
                           @RequestParam(name = "place-area") int area,
                           @RequestParam(name = "place-country") String country){
        String redirect = "/place/add-place?error";

        Place place = new Place();
        place.setName(name);
        place.setArea(area);
        place.setCountry(country);

        if (placeService.addPlace(place) != null) {
            redirect = "/place/home";
        }

        return "redirect:" + redirect;
    }

    @GetMapping(value = "/home")
    public String openHome(Model model) {
        List<Place> places = placeService.getAllPlaces();
        model.addAttribute("places",places);
        return "home";
    }

    @GetMapping(value = "/details/{id}")
    public String openDetails(@PathVariable(name = "id") Long id, Model model) {
        Place place = placeService.getPlace(id);
        model.addAttribute("place", place);
        return "details";
    }

    @PostMapping(value = "/update")
    public String updatePlace(@RequestParam(name = "place-id") Long id,
                              @RequestParam(name = "place-name") String name,
                              @RequestParam(name = "place-area") int area,
                              @RequestParam(name = "place-country") String country,
                              @RequestParam(name = "place-photo") MultipartFile file) {
        String redirect = "/place/update?error";

        Place place = placeService.getPlace(id);
        place.setName(name);
        place.setArea(area);
        place.setCountry(country);

        if (!file.isEmpty()) {
            fileService.uploadPhoto(file, id);
        }

        place.setPicture("photo" + id);


        if (placeService.updatePlace(place) != null) {
            redirect = "/place/home";
        }

        return "redirect:" + redirect;

    }
}
