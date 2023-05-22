package kz.bitlab.springresttest2.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springresttest2.entities.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DoorRepository extends JpaRepository<Door,Long> {
    Door findAllById(Long id);
}
