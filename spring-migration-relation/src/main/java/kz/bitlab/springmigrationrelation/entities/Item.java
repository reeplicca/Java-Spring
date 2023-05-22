package kz.bitlab.springmigrationrelation.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brands;
}
