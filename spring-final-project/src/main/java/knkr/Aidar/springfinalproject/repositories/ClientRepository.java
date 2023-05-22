package knkr.Aidar.springfinalproject.repositories;

import jakarta.transaction.Transactional;
import knkr.Aidar.springfinalproject.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByEmail(String email);
}
