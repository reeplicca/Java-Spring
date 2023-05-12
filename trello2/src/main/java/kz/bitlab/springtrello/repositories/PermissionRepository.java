package kz.bitlab.springtrello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springtrello.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
