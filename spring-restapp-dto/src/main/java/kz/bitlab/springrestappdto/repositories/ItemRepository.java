package kz.bitlab.springrestappdto.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springrestappdto.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item,Long> {
    Item findAllById(Long id);
}
