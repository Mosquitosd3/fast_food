package dsih.service;

import domain.model.Dish;

import java.util.List;

public interface DishService {
    Dish save(Dish dish);
    List<Dish> findAllDish();
    Dish finDishById(int id);
    Dish findDishByName(String name);
    boolean deleteDish(int id);
}
