package order.controller;

import domain.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/order")
public class KafkaProducerController {
    private KafkaTemplate<Integer, Order> template;

    public KafkaProducerController(KafkaTemplate<Integer, Order> template) {
        this.template = template;
    }

    @PostMapping
    public void sendOrder(@RequestBody Order order) {
        order.setCreated(LocalDateTime.now());
        template.send("order", order);
    }
}
