package kz.bitlab.springsecuritytest.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springsecuritytest.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
}
