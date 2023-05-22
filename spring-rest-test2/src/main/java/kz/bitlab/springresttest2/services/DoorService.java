package kz.bitlab.springresttest2.services;

import kz.bitlab.springresttest2.entities.DoorDto;

import java.util.List;

public interface DoorService {
    DoorDto addDoor(DoorDto doorDto);
    List<DoorDto> getAllDoors();
    DoorDto getDoor(Long id);
    DoorDto updateDoor(DoorDto updDoorDto);
    void deleteDoor(Long id);
}
