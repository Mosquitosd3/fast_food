package dsih.controller;

import domain.model.Dish;
import dsih.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @PostMapping
    public Dish save(@RequestBody Dish dish) {
        return this.service.add(dish);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Dish dish) {
        boolean status = service.update(dish);
        return ResponseEntity.status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam int id) {
        boolean status = service.deleteDish(id);
        return ResponseEntity.status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    @GetMapping("/getById")
    public ResponseEntity<Dish> getById(@RequestParam int id) {
        var dish = service.finDishById(id);
        return new ResponseEntity<Dish>(
                dish.orElse(new Dish()),
                dish.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/getByName")
    public List<Dish> getByName(@RequestParam String key) {
        return service.findDishByName(key);
    }

    @GetMapping("/getAll")
    public List<Dish> getAll() {
        return service.findAllDish();
    }
}
