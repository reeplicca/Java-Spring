package kz.bitlab.springrestapp.services;

import kz.bitlab.springrestapp.entities.Item;

import java.util.List;

public interface ItemService {
    Item addItem(Item item);
    List<Item> getAllItem();
    Item getItem(Long id);
    Item updateItem(Item updItem);
    void deleteItem(Long id);
}
