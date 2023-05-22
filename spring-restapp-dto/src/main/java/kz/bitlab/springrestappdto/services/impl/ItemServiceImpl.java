package kz.bitlab.springrestappdto.services.impl;

import kz.bitlab.springrestappdto.entities.Item;
import kz.bitlab.springrestappdto.entities.ItemDto;
import kz.bitlab.springrestappdto.mapper.ItemMapper;
import kz.bitlab.springrestappdto.repositories.ItemRepository;
import kz.bitlab.springrestappdto.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setAmount(itemDto.getAmount());
        item.setPromocode(UUID.randomUUID().toString());
        item.setBrands(itemDto.getBrands());
        return itemMapper.mapToDto(itemRepository.save(item));
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemMapper.mapToDtoList(itemRepository.findAll());
    }

    @Override
    public ItemDto getItem(Long id) {
        return itemMapper.mapToDto(itemRepository.findAllById(id));
    }

    @Override
    public ItemDto updateItem(ItemDto updItemDto) {
        Item item = itemRepository.findAllById(updItemDto.getId());
        item.setName(updItemDto.getName());
        item.setPrice(updItemDto.getPrice());
        item.setAmount(updItemDto.getAmount());
        item.setBrands(updItemDto.getBrands());

        return itemMapper.mapToDto(itemRepository.save(item));
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
