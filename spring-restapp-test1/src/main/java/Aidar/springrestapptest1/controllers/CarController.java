package Aidar.springrestapptest1.controllers;

import Aidar.springrestapptest1.entities.Car;
import Aidar.springrestapptest1.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    @GetMapping
    public List<Car> getAllCar() {
        return carService.getAllCar();
    }


}
