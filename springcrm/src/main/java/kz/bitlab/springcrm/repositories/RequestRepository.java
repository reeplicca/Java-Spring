package kz.bitlab.springcrm.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springcrm.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request,Long> {
    Request findAllById(Long id);
}
