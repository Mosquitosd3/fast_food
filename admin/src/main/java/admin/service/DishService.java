package admin.service;

import domain.model.Dish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class DishService {
    private final RestTemplate rest;

    @Value("${dish.api.findAll}")
    private String urlDishFindAll;

    public DishService(RestTemplate rest) {
        this.rest = rest;
    }

    public List<Dish> findAllDishes() {
        List<Dish> body = rest.exchange(
                urlDishFindAll, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Dish>>() {
                }
        ).getBody();
        return body == null ? Collections.emptyList() : body;
    }
}
