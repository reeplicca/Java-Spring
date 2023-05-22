package kz.bitlab.springrestappdto.mapper;

import kz.bitlab.springrestappdto.entities.Item;
import kz.bitlab.springrestappdto.entities.ItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemDto> mapToDtoList(List<Item> items);
    ItemDto mapToDto(Item item);

    Item maptoEntity(ItemDto itemDto);
}
