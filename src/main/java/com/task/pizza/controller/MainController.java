package com.task.pizza.controller;

import com.task.pizza.dto.Bill;
import com.task.pizza.dto.OrderDto;
import com.task.pizza.service.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//@RestController
@Controller
public class MainController {

    private final CounterService counterService;

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    public MainController( CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        return "main";
    }

    @PostMapping("/order")
    public Bill generateBill(@RequestBody OrderDto orderDto) {
        logger.trace("Generating new bill for order... ");
        return counterService.count(orderDto);
    }

}
