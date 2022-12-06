package dsih.repository.mem;

import domain.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class MemDishRepository {
    private final Map<Integer, Dish> store = new ConcurrentHashMap<>();
    private final AtomicInteger generate = new AtomicInteger(2);

    public MemDishRepository() {
        Dish burger = new Dish(1, "burger", "desc", 199.0);
        Dish free = new Dish(2, "Free", "desc", 95.0);
        store.put(1, burger);
        store.put(2, free);
    }

    public Dish save(Dish dish) {
        int id = generate.incrementAndGet();
        dish.setId(id);
        return store.put(id, dish);
    }

    public boolean update(Dish dish) {
        if (findById(dish.getId()).isPresent()) {
            store.put(dish.getId(), dish);
            return true;
        } else {
            return false;
        }
    }
    public Collection<Dish> findALl() {
        return store.values();
    }

    public Optional<Dish> findById(int id) {
        return Optional.of(store.get(id));
    }

    public List<Dish> findByName(String name) {
        return
                store.values().stream()
                        .filter((el) -> el.getName().contains(name))
                        .collect(Collectors.toList()
                        );
    }

    public boolean delete(Dish dish) {
        if (findById(dish.getId()).isPresent()) {
            store.remove(dish.getId());
            return true;
        } else {
            return false;
        }
    }
}
