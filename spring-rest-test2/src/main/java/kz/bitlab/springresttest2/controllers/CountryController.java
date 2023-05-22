package kz.bitlab.springresttest2.controllers;

import kz.bitlab.springresttest2.entities.CountryDto;
import kz.bitlab.springresttest2.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping
    public CountryDto addCountry(@RequestBody CountryDto countryDto) {
        return countryService.addCountry(countryDto);
    }

    @GetMapping
    public List<CountryDto> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping(value = "/{id}")
    public CountryDto getCountry(@PathVariable(name = "id") Long id) {
        return countryService.getCountry(id);
    }

    @PutMapping
    public CountryDto updateCountry(@RequestBody CountryDto updateCountryDto) {
        return countryService.updateCountry(updateCountryDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCountry(@PathVariable(name = "id") Long id) {
        countryService.deleteCountry(id);
    }
}
