package com.task.pizza.service;

import com.task.pizza.dto.BillRecord;
import com.task.pizza.dto.OrderDto;
import com.task.pizza.dto.OrderRecord;
import com.task.pizza.dto.Bill;
import com.task.pizza.domain.Pizza;
import com.task.pizza.repo.BillRecordRepo;
import com.task.pizza.repo.BillRepo;
import com.task.pizza.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CounterServiceImpl implements CounterService {

    private final PizzaRepo pizzaRepo;

    private final BillRepo billRepo;

    private final BillRecordRepo billRecordRepo;


    @Autowired
    public CounterServiceImpl(PizzaRepo pizzaRepo, BillRepo billRepo, BillRecordRepo billRecordRepo) {
        this.pizzaRepo = pizzaRepo;
        this.billRepo = billRepo;
        this.billRecordRepo = billRecordRepo;
    }

    @Override
    public Bill count(OrderDto orderDto) {
        double total = 0;
        Bill bill = new Bill();
        ArrayList<BillRecord> pizzas = new ArrayList<>();
        for (OrderRecord orderRecord : orderDto.getPizzas()) {
            Pizza orderedPizza = pizzaRepo.findById(orderRecord.getItemId()).get();
            BillRecord billRecord = new BillRecord(orderedPizza.getName(), orderRecord.getAmount());
            billRecordRepo.save(billRecord);
            pizzas.add(billRecord);
            total += orderedPizza.getPrice() * orderRecord.getAmount();
        }
        bill.setPizzas(pizzas);
        bill.setTotal(total);
        billRepo.save(bill);
        return bill;
    }
}
