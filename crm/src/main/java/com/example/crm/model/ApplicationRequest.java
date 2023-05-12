package com.example.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_applicationrequest")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userName")
    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Courses courseName;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "phone")
    private String phone;

    @Column(name = "handled")
    private boolean handled;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Operators> operators;
}
