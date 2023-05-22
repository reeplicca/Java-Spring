package kz.bitlab.springrestappdto.controllers;

import kz.bitlab.springrestappdto.entities.ItemDto;
import kz.bitlab.springrestappdto.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ItemDto addItem(@RequestBody ItemDto itemDto) {
        return itemService.addItem(itemDto);
    }

    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping(value = "/{id}")
    public ItemDto getItem(@PathVariable(name = "id") Long id) {
        return itemService.getItem(id);
    }

    @PutMapping
    public ItemDto updateItem(@RequestBody ItemDto updItemDto) {
        return itemService.updateItem(updItemDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteItem(@PathVariable(name = "id") Long id) {
        itemService.deleteItem(id);
    }

}
