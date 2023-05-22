package kz.bitlab.springrestappdto.services;

import kz.bitlab.springrestappdto.entities.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto addItem(ItemDto itemDto);
    List<ItemDto> getAllItems();
    ItemDto getItem(Long id);
    ItemDto updateItem(ItemDto updItemDto);
    void deleteItem(Long id);
}
