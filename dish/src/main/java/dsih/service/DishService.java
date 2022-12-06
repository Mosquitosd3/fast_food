package dsih.service;

import domain.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {
    Dish add(Dish dish);
    boolean update(Dish dish);
    List<Dish> findAllDish();
    Optional<Dish> finDishById(int id);
    List<Dish> findDishByName(String name);
    boolean deleteDish(int id);
}
