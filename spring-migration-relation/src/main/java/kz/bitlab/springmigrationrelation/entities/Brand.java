package kz.bitlab.springmigrationrelation.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
