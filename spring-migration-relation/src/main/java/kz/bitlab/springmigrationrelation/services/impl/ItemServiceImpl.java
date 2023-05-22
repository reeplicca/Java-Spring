package kz.bitlab.springmigrationrelation.services.impl;

import kz.bitlab.springmigrationrelation.entities.Item;
import kz.bitlab.springmigrationrelation.repositories.ItemRepository;
import kz.bitlab.springmigrationrelation.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
