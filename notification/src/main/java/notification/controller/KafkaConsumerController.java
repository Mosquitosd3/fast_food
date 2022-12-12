package notification.controller;

import domain.model.Order;

import notification.model.Notification;
import notification.service.NotificationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerController {
    private final NotificationService service;

    public KafkaConsumerController(NotificationService service) {
        this.service = service;
    }

    @KafkaListener(topics = {"order"})
    public void onApiCall(ConsumerRecord<Integer, Order> input) {
        service.saveOrder(new Notification(input.value()));
    }
}
