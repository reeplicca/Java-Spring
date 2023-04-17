package kz.kenzhakhimov.goodplaces.repositories;

import kz.kenzhakhimov.goodplaces.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PlaceRepository extends JpaRepository<Place,Long> {
    Place findAllById(Long id);
}
