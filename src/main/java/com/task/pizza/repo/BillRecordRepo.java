package com.task.pizza.repo;

import com.task.pizza.dto.BillRecord;
import org.springframework.data.repository.CrudRepository;

public interface BillRecordRepo extends CrudRepository<BillRecord, Long> {

}
