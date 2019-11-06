package com.task.pizza.service;

import com.task.pizza.domain.Pizza;
import com.task.pizza.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepo pizzaRepo;

    @Autowired
    public PizzaServiceImpl(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    @Override
    public List<Pizza> pizzaList() {

        ArrayList<Pizza> pizzaList = new ArrayList<>();

        Iterable<Pizza> pizzaIterable = pizzaRepo.findAll();
        pizzaIterable.forEach(pizzaList::add);
        return pizzaList;
    }
}
