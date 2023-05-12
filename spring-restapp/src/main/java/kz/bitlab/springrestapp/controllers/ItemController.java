package kz.bitlab.springrestapp.controllers;

import kz.bitlab.springrestapp.entities.Item;
import kz.bitlab.springrestapp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItem();
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @GetMapping(value = "/{id}")
    public Item getItem(@PathVariable(name = "id") Long id) {
        return itemService.getItem(id);
    }

    @PutMapping
    public Item updateItem(@RequestBody Item updItem) {
        return itemService.updateItem(updItem);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteItem(@PathVariable(name = "id") Long id) {
        itemService.deleteItem(id);
    }
}
