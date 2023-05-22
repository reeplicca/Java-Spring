package kz.bitlab.springrestappdto.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int amount;
    private String promocode;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Brand> brands;
}
