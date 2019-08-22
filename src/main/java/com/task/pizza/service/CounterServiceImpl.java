package com.task.pizza.service;

import com.task.pizza.domain.Drink;
import com.task.pizza.domain.Extra;
import com.task.pizza.dto.BillRecord;
import com.task.pizza.dto.OrderDto;
import com.task.pizza.dto.OrderRecord;
import com.task.pizza.dto.Bill;
import com.task.pizza.domain.Pizza;
import com.task.pizza.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CounterServiceImpl implements CounterService {

    private final PizzaRepo pizzaRepo;

    private final BillRepo billRepo;

    private final BillRecordRepo billRecordRepo;

    private final DrinkRepo drinkRepo;

    private final ExtraRepo extraRepo;


    @Autowired
    public CounterServiceImpl(PizzaRepo pizzaRepo, BillRepo billRepo, BillRecordRepo billRecordRepo, DrinkRepo drinkRepo, ExtraRepo extraRepo) {
        this.pizzaRepo = pizzaRepo;
        this.billRepo = billRepo;
        this.billRecordRepo = billRecordRepo;
        this.drinkRepo = drinkRepo;
        this.extraRepo = extraRepo;
    }

    @Override
    public Bill count(OrderDto orderDto) {
        double total = 0;
        Bill bill = new Bill();
        DecimalFormat formatter = new  DecimalFormat("#0.00");

        if (orderDto.isHasPizza()) {
            total += countPizzas(orderDto, bill);
        } else {
            bill.setPizzas(new ArrayList<>());
        }

        if (orderDto.isHasDrink()) {
            total += countDrinks(orderDto, bill);
        } else {
            bill.setDrinks(new ArrayList<>());
        }


        if (orderDto.isHasExtras()) {
            total += countExtras(orderDto, bill);
        } else {
            bill.setExtras(new ArrayList<>());
        }
        bill.setCreatedAt(LocalDateTime.now());
        bill.setTotal(Double.parseDouble(formatter.format(total)));
        billRepo.save(bill);
        return bill;
    }

    private double countPizzas(OrderDto orderDto, Bill bill) {
        double subtotal = 0;
        ArrayList<BillRecord> pizzas = new ArrayList<>();
        for (OrderRecord orderRecord : orderDto.getPizzas()) {
            Pizza orderedPizza = pizzaRepo.findById(orderRecord.getItemId()).get();
            BillRecord billRecord = new BillRecord(orderedPizza.getName(), orderRecord.getAmount());
            billRecordRepo.save(billRecord);
            pizzas.add(billRecord);
            subtotal += orderedPizza.getPrice() * orderRecord.getAmount();
        }
        bill.setPizzas(pizzas);
        return subtotal;
    }

    private double countDrinks(OrderDto orderDto, Bill bill) {
        double subtotal = 0;
        ArrayList<BillRecord> drinks = new ArrayList<>();
        for (OrderRecord orderRecord : orderDto.getDrinks()) {
            Drink orderedDrink = drinkRepo.findById(orderRecord.getItemId()).get();
            BillRecord billRecord = new BillRecord(orderedDrink.getName(), orderRecord.getAmount());
            billRecordRepo.save(billRecord);
            drinks.add(billRecord);
            subtotal += orderedDrink.getPrice() * orderRecord.getAmount();
        }
        bill.setDrinks(drinks);
        return subtotal;
    }

    private double countExtras(OrderDto orderDto, Bill bill) {
        double subtotal = 0;
        ArrayList<BillRecord> extras = new ArrayList<>();
        for (OrderRecord orderRecord : orderDto.getExtras()) {
            Extra orderedExtra = extraRepo.findById(orderRecord.getItemId()).get();
            BillRecord billRecord = new BillRecord(orderedExtra.getName(), orderRecord.getAmount());
            billRecordRepo.save(billRecord);
            extras.add(billRecord);
            subtotal += orderedExtra.getPrice() * orderRecord.getAmount();
        }
        bill.setExtras(extras);
        return subtotal;

    }

}
