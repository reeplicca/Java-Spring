package Aidar.springrestapptest1.repositories;

import Aidar.springrestapptest1.entities.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car,Long> {
    Car findAllById(Long id);
}
