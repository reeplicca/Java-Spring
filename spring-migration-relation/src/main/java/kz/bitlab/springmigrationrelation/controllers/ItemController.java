package kz.bitlab.springmigrationrelation.controllers;

import kz.bitlab.springmigrationrelation.entities.Item;
import kz.bitlab.springmigrationrelation.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}
