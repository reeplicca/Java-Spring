package Aidar.springrestapptest1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "cars")
@Getter
@Setter
public class Car extends BaseEntity{
    private String name;
    private String model;
    private int mileage;
    private double volume;
    private String color;

}
