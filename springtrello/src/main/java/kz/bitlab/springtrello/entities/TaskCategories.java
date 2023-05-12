package kz.bitlab.springtrello.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "taskcategories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
