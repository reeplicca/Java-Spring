package kz.bitlab.springrestapp.services.impl;

import kz.bitlab.springrestapp.entities.Item;
import kz.bitlab.springrestapp.repositories.ItemRepository;
import kz.bitlab.springrestapp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.findAllById(id);
    }

    @Override
    public Item updateItem(Item updItem) {
        Item item = getItem(updItem.getId());
        item.setName(updItem.getName());
        item.setPrice(updItem.getPrice());
        item.setAmount(updItem.getAmount());

        return addItem(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
