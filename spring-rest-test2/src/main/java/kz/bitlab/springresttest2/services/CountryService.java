package kz.bitlab.springresttest2.services;

import kz.bitlab.springresttest2.entities.CountryDto;

import java.util.List;

public interface CountryService {
    CountryDto addCountry(CountryDto countryDto);
    List<CountryDto> getAllCountries();
    CountryDto getCountry(Long id);
    CountryDto updateCountry(CountryDto updateCountryDto);
    void deleteCountry(Long id);
}
