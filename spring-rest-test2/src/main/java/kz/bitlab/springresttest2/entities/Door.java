package kz.bitlab.springresttest2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Door {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private int price;
    private double size;
    private String serial;
    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;
}
