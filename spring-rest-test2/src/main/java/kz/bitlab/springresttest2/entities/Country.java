package kz.bitlab.springresttest2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "countries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String capital;
    private String hashCode;
}
