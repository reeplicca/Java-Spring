package kz.bitlab.springtrello.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_folders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<TaskCategories> categories;

}
