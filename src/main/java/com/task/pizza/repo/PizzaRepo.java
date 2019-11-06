package com.task.pizza.repo;

import com.task.pizza.domain.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepo extends CrudRepository<Pizza, Long> {

}
