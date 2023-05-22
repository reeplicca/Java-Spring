package kz.bitlab.springresttest2.services.impl;

import kz.bitlab.springresttest2.entities.Country;
import kz.bitlab.springresttest2.entities.CountryDto;
import kz.bitlab.springresttest2.mappers.CountryMapper;
import kz.bitlab.springresttest2.repositories.CountryRepository;
import kz.bitlab.springresttest2.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryMapper countryMapper;
    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        Country country = Country.builder()
                .name(countryDto.getName())
                .capital(countryDto.getCapital())
                .hashCode(UUID.randomUUID().toString())
                .build();

        return countryMapper.mapToDto(countryRepository.save(country));
    }

    @Override
    public List<CountryDto> getAllCountries() {
        return countryMapper.mapToDtoList(countryRepository.findAll());
    }

    @Override
    public CountryDto getCountry(Long id) {
        return countryMapper.mapToDto(countryRepository.findAllById(id));
    }

    @Override
    public CountryDto updateCountry(CountryDto updateCountryDto) {
        Country country = countryRepository.findAllById(updateCountryDto.getId());
        country.setName(updateCountryDto.getName());
        country.setCapital(updateCountryDto.getCapital());

        return countryMapper.mapToDto(countryRepository.save(country));
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
