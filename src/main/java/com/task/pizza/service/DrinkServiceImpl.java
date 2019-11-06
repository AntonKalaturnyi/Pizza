package com.task.pizza.service;

import com.task.pizza.domain.Drink;
import com.task.pizza.repo.DrinkRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepo drinkRepo;

    @Autowired
    public DrinkServiceImpl(DrinkRepo drinkRepo) {
        this.drinkRepo = drinkRepo;
    }

    @Override
    public List<Drink> drinkList() {

        ArrayList<Drink> drinkList = new ArrayList<>();

        Iterable<Drink> drinkIterable = drinkRepo.findAll();
        drinkIterable.forEach(drinkList::add);
        return drinkList;
    }
}
