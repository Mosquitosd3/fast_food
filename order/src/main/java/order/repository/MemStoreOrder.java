package order.repository;

import domain.model.Order;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemStoreOrder {
    private final Map<Integer, Order> store = new ConcurrentHashMap<>();
    private final AtomicInteger generate = new AtomicInteger(2);

    public MemStoreOrder() {
        store.put(1,
                new Order(1,
                        null,
                        null,
                        new ArrayList<>(),
                        100.0,
                        LocalDateTime.now(),
                        false
                )
        );
        store.put(2,
                new Order(2,
                        null,
                        null,
                        new ArrayList<>(),
                        75.10,
                        LocalDateTime.now(),
                        false
                )
        );
    }

    public Order save(Order order) {
        if (order.getId() == 0) {
            return create(order);
        } else {
            return update(order);
        }
    }

    private Order create(Order order) {
        int id = generate.incrementAndGet();
        order.setId(id);
        return this.store.put(id, order);
    }

    private Order update(Order order) {
        return this.store.put(order.getId(), order);
    }

    public Collection<Order> findALl() {
        return this.store.values();
    }

    public Optional<Order> findById(int id) {
        return Optional.of(this.store.get(id));
    }

    public boolean delete(Order order) {
        if (findById(order.getId()).isPresent()) {
            this.store.remove(order.getId());
            return true;
        } else {
            return false;
        }
    }
}
