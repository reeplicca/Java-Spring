package kz.bitlab.springresttest2.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springresttest2.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findAllById(Long id);
}
