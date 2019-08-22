package com.task.pizza.controller;

import com.task.pizza.dto.Bill;
import com.task.pizza.dto.OrderDto;
import com.task.pizza.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {


    private final CounterService counterService;

    @Autowired
    public MainController( CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }



    @PostMapping("/order")
    public Bill generateBill(@RequestBody OrderDto orderDto) {
        return counterService.count(orderDto);
    }

}
