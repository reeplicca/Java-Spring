package kz.bitlab.springresttest2.mappers;

import kz.bitlab.springresttest2.entities.Door;
import kz.bitlab.springresttest2.entities.DoorDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoorMapper {
    List<DoorDto> mapToDtoList(List<Door> doors);
    DoorDto mapToDto(Door door);
    Door mapToEntity(DoorDto doorDto);
}
