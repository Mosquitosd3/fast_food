package notification.service;

import domain.model.Order;
import notification.repository.memRep.NotificationMemStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationMemStore store;

    public NotificationService(NotificationMemStore store) {
        this.store = store;
    }

    public Order saveOrder(Order order) {
        return store.save(order);
    }

    public List<Order> findAllOrder() {
        return new ArrayList<>(store.findAll());
    }
}
