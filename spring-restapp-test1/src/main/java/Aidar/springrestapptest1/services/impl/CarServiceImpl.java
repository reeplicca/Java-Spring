package Aidar.springrestapptest1.services.impl;

import Aidar.springrestapptest1.entities.Car;
import Aidar.springrestapptest1.repositories.CarRepository;
import Aidar.springrestapptest1.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCar() {
        return carRepository.findAll();
    }

    @Override
    public Car getCar(Long id) {
        return carRepository.findAllById(id);
    }

    @Override
    public Car updateCar(Car updCar) {
        return null;
    }

    @Override
    public void deleteCar(Long id) {

    }
}
