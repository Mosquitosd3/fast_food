package notification.repository.memRep;

import domain.model.Order;
import domain.model.Role;
import domain.model.User;
import notification.model.Notification;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class NotificationMemStore {
    private final Map<Integer, Notification> store =  new ConcurrentHashMap<>();
    private final AtomicInteger generate = new AtomicInteger(1);

    public NotificationMemStore() {
        User user = new User(1,
                "Test",
                "test",
                "123",
                "+7(921)",
                Role.USER
        );
        Order order = new Order(
                1,
                user,
                new ArrayList<>(),
                200.10,
                LocalDateTime.now(),
                false
        );

        store.put(1, new Notification(order));
    }

    public Notification save(Notification notification) {
        if (notification.getId() == 0) {
            int id = generate.incrementAndGet();
            notification.setId(id);
            return store.put(id, notification);
        } else {
            return store.put(notification.getId(), notification);
        }
    }

    public Collection<Notification> findAll() {
        return store.values();
    }
}
