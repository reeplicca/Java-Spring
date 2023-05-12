package Aidar.springrestapptest1.services;

import Aidar.springrestapptest1.entities.Car;

import java.util.List;

public interface CarService {
    Car addCar(Car car);
    List<Car> getAllCar();
    Car getCar(Long id);
    Car updateCar(Car updCar);
    void deleteCar(Long id);
}
