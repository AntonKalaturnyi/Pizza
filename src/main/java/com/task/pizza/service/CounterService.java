package com.task.pizza.service;

import com.task.pizza.dto.OrderDto;
import com.task.pizza.dto.Bill;

public interface CounterService {

    Bill count(OrderDto orderDto);
}
