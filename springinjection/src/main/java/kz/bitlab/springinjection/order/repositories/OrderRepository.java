package kz.bitlab.springinjection.order.repositories;

import kz.bitlab.springinjection.order.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findAllById(Long id);
}
