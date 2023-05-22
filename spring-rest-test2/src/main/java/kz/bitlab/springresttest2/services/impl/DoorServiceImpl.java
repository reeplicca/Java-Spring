package kz.bitlab.springresttest2.services.impl;

import kz.bitlab.springresttest2.entities.Door;
import kz.bitlab.springresttest2.entities.DoorDto;
import kz.bitlab.springresttest2.mappers.CountryMapper;
import kz.bitlab.springresttest2.mappers.DoorMapper;
import kz.bitlab.springresttest2.repositories.DoorRepository;
import kz.bitlab.springresttest2.services.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoorServiceImpl implements DoorService {
    @Autowired
    private DoorRepository doorRepository;
    @Autowired
    private DoorMapper doorMapper;
    @Autowired
    private CountryMapper countryMapper;
    @Override
    public DoorDto addDoor(DoorDto doorDto) {
        Door door = Door.builder()
                .model(doorDto.getModel())
                .price(doorDto.getPrice())
                .size(doorDto.getSize())
                .serial(UUID.randomUUID().toString())
                .country(countryMapper.mapToEntity(doorDto.getCountryDto()))
                .build();

        return doorMapper.mapToDto(doorRepository.save(door));
    }

    @Override
    public List<DoorDto> getAllDoors() {
        return doorMapper.mapToDtoList(doorRepository.findAll());
    }

    @Override
    public DoorDto getDoor(Long id) {
        return doorMapper.mapToDto(doorRepository.findAllById(id));
    }

    @Override
    public DoorDto updateDoor(DoorDto updDoorDto) {
        Door door = doorRepository.findAllById(updDoorDto.getId());
        door.setModel(updDoorDto.getModel());
        door.setPrice(updDoorDto.getPrice());
        door.setSize(updDoorDto.getSize());
        door.setCountry(countryMapper.mapToEntity(updDoorDto.getCountryDto()));

        return doorMapper.mapToDto(doorRepository.save(door));
    }

    @Override
    public void deleteDoor(Long id) {
        doorRepository.deleteById(id);
    }
}
