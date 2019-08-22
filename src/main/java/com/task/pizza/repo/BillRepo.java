package com.task.pizza.repo;

import com.task.pizza.dto.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepo extends CrudRepository<Bill, Long> {

}
