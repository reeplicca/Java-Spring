package kz.bitlab.springresttest2.controllers;

import kz.bitlab.springresttest2.entities.DoorDto;
import kz.bitlab.springresttest2.services.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class DoorController {
    @Autowired
    private DoorService doorService;

    @PostMapping
    public DoorDto addDoor(@RequestBody DoorDto doorDto) {
        return doorService.addDoor(doorDto);
    }

    @GetMapping
    public List<DoorDto> getAllDoors() {
        return doorService.getAllDoors();
    }

    @GetMapping(value = "/{id}")
    public DoorDto getDoor(@PathVariable(name = "id") Long id) {
        return doorService.getDoor(id);
    }

    @PutMapping
    public DoorDto updateDoor(@RequestBody DoorDto updateDoorDto) {
        return doorService.updateDoor(updateDoorDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDoor(@PathVariable(name = "id") Long id) {
        doorService.deleteDoor(id);
    }
}
