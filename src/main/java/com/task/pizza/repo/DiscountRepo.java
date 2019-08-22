package com.task.pizza.repo;

import com.task.pizza.domain.Discount;
import org.springframework.data.repository.CrudRepository;

public interface DiscountRepo extends CrudRepository<Discount, Long> {

    Discount findByName(String name);
}
