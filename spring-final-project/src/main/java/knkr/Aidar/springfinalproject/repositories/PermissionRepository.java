package knkr.Aidar.springfinalproject.repositories;

import jakarta.transaction.Transactional;
import knkr.Aidar.springfinalproject.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    Permission findByRole(String role);
}
