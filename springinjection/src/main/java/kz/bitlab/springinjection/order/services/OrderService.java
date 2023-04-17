package kz.bitlab.springinjection.order.services;

import kz.bitlab.springinjection.order.entities.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(Order order);
    List<Order> getAllOrder();
    Order getOrder(Long id);
    Order saveOrder(Order order);
    void deleteOrder(Long id);
}
