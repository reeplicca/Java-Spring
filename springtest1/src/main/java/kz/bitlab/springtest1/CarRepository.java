package kz.bitlab.springtest1;

import kz.bitlab.springtest1.DB.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car,Long> {
    Car findAllById(Long id);
}
