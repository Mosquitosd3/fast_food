package order.service;

import domain.model.Order;
import order.repository.MemStoreOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService implements OrderService {
    private final MemStoreOrder storeOrder;

    public DeliveryService(MemStoreOrder storeOrder) {
        this.storeOrder = storeOrder;
    }

    @Override
    public Order save(Order order) {
        return storeOrder.save(order);
    }

    @Override
    public List<Order> findAllOrder() {
        return new ArrayList<>(storeOrder.findALl());
    }

    @Override
    public Optional<Order> findOrderById(int id) {
        return storeOrder.findById(id);
    }

    @Override
    public boolean deleteOrder(int id) {
        Order order = new Order();
        order.setId(id);
        return storeOrder.delete(order);
    }
}
