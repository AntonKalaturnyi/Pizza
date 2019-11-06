package com.task.pizza.controller;

import com.task.pizza.dto.Bill;
import com.task.pizza.dto.OrderDto;
import com.task.pizza.service.CounterService;
import com.task.pizza.service.DrinkService;
import com.task.pizza.service.ExtraService;
import com.task.pizza.service.PizzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class MainController {

    private final CounterService counterService;

    private PizzaService pizzaService;

    private DrinkService drinkService;

    private ExtraService extraService;

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    public MainController(CounterService counterService, PizzaService pizzaServise, PizzaService pizzaService, DrinkService drinkService) {
        this.counterService = counterService;
        this.pizzaService = pizzaService;
        this.drinkService = drinkService;
    }


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model){
        model.addAttribute("pizzas", pizzaService.pizzaList());
        model.addAttribute("drinks", drinkService.drinkList());
        model.addAttribute("pizzas", pizzaService.pizzaList());

        logger.info("=== Main page requested ===");
        return "main";
    }

    @PostMapping("/order")
    public Bill generateBill(@RequestBody OrderDto orderDto) {
        logger.trace("Generating new bill for ororderder... ");
        return counterService.count(orderDto);
    }

}
