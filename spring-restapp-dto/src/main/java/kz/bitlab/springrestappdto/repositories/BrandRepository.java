package kz.bitlab.springrestappdto.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springrestappdto.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BrandRepository extends JpaRepository<Brand,Long> {
    Brand findAllById(Long id);
}
