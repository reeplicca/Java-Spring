package kz.kenzhakhimov.goodplaces.controllers;

import kz.kenzhakhimov.goodplaces.entity.Place;
import kz.kenzhakhimov.goodplaces.services.FileService;
import kz.kenzhakhimov.goodplaces.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value="/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @Autowired
    private FileService fileService;
    @GetMapping(value="/add-places")
    public String openAddPlaces(){
        return "add-places";
    }
    @PostMapping(value="/add-places")
    public String addPlacesPost(@RequestParam(name="place-name") String name,
                                @RequestParam(name="place-area") int area,
                                @RequestParam(name="place-country") String country){
        String redirect = "/places/add-places?error";
        Place place = new Place();
        place.setName(name);
        place.setArea(area);
        place.setCountry(country);
        if(placeService.addPlace(place) != null){
            redirect = "/places/home";
        }
        return "redirect:" + redirect;
    }
    @GetMapping(value="/home")
    public String openHome(Model model){
        List<Place> places = placeService.getAllPlaces();
        model.addAttribute("places",places);
        return "home";
    }
    @GetMapping(value="/details/{id}")
    public String openDetails(@PathVariable("id") Long id,Model model){
        Place place = placeService.getPlace(id);
        model.addAttribute("place",place);
        return "details";
    }
    @PostMapping(value="/upload-photo")
    public String uploadPhotoPost(@RequestParam(name="place-photo")MultipartFile file){
        fileService.uploadPhoto(file);
        return "redirect:home";
    }
}
