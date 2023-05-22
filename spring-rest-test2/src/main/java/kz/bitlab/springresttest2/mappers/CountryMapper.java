package kz.bitlab.springresttest2.mappers;

import kz.bitlab.springresttest2.entities.Country;
import kz.bitlab.springresttest2.entities.CountryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    List<CountryDto> mapToDtoList(List<Country> countries);
    CountryDto mapToDto(Country country);
    Country mapToEntity(CountryDto countryDto);
}
