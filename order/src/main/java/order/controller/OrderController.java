package order.controller;

import domain.model.Order;
import order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> findAll() {
        return this.service.findAllOrder();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable int id) {
        var order = this.service.findOrderById(id);
        return new ResponseEntity<Order>(
                order.orElse(new Order()),
                order.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping
    public ResponseEntity<Order> add(@RequestBody Order order) {
        return new ResponseEntity<Order>(
                this.service.save(order),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Order order) {
        service.save(order);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
       service.deleteOrder(id);
       return ResponseEntity.ok().build();
    }
}