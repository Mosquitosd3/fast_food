package dsih.service;

import domain.model.Dish;
import dsih.repository.mem.MemDishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImp implements DishService {
    private final MemDishRepository repository;

    public DishServiceImp(MemDishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish add(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public boolean update(Dish dish) {
        return repository.update(dish);
    }

    @Override
    public List<Dish> findAllDish() {
        return new ArrayList<>(repository.findALl());
    }

    @Override
    public Optional<Dish> finDishById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Dish> findDishByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public boolean deleteDish(int id) {
        Dish dish = new Dish();
        dish.setId(id);
        return repository.delete(dish);
    }
}
