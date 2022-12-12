package notification.model;

import domain.model.Dish;
import domain.model.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notification {
    @EqualsAndHashCode.Include
    private int id;
    private String nameUser;
    private String numberPhone;
    private String email;
    private List<Dish> dishes = new ArrayList<>();
    private double totalPrice;
    private LocalDateTime created;
    private boolean completed;

    public Notification(Order order) {
        this.nameUser = order.getConsumer().getName();
        this.numberPhone = order.getConsumer().getNumberPhone();
        this.email = order.getConsumer().getEmail();
        this.dishes = order.getDishes();
        this.totalPrice = order.getTotalPrice();
        this.created = order.getCreated();
        this.completed = order.isCompleted();
    }
}
