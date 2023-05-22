package kz.bitlab.springrestappdto.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private int amount;
    private List<Brand> brands;
}
