package order.service;

import domain.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);
    List<Order> findAllOrder();
    Optional<Order> findOrderById(int id);
    boolean deleteOrder(int id);
}
