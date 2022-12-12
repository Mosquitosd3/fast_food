package notification.service;

import domain.model.Notification;
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

    public Notification saveOrder(Notification notification) {
        return store.save(notification);
    }

    public List<Notification> findAllOrder() {
        return new ArrayList<>(store.findAll());
    }
}
