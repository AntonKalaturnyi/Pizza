package com.task.pizza.service;

import com.task.pizza.domain.Discount;
import com.task.pizza.domain.Drink;
import com.task.pizza.domain.Extra;
import com.task.pizza.domain.Pizza;
import com.task.pizza.dto.Bill;
import com.task.pizza.dto.BillRecord;
import com.task.pizza.dto.OrderDto;
import com.task.pizza.dto.OrderRecord;
import com.task.pizza.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

@Service
public class CounterServiceImpl implements CounterService {

    private final PizzaRepo pizzaRepo;

    private final BillRepo billRepo;

    private final BillRecordRepo billRecordRepo;

    private final DrinkRepo drinkRepo;

    private final ExtraRepo extraRepo;

    private final DiscountRepo discountRepo;

    private static final DecimalFormat formatter = new  DecimalFormat("#0.00");


    @Autowired
    public CounterServiceImpl(PizzaRepo pizzaRepo, BillRepo billRepo, BillRecordRepo billRecordRepo, DrinkRepo drinkRepo, ExtraRepo extraRepo, DiscountRepo discountRepo) {
        this.pizzaRepo = pizzaRepo;
        this.billRepo = billRepo;
        this.billRecordRepo = billRecordRepo;
        this.drinkRepo = drinkRepo;
        this.extraRepo = extraRepo;
        this.discountRepo = discountRepo;
    }

    @Override
    public Bill count(OrderDto orderDto) {
        double total = 0;
        double tips = 0;
        double totalWithTips = 0;
        double discountAmount = 0;
        Bill bill = new Bill();

        bill.setCreatedAt(LocalDateTime.now());

        // Free monday coffee discount
      //  bill.setCreatedAt(LocalDateTime.of(2019, Month.AUGUST, 26, 14, 15));

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

//        bill.setCreatedAt(LocalDateTime.of(2019, Month.AUGUST, 24, 14, 15));  test Independence day discount

        if (bill.getCreatedAt().getDayOfWeek() == DayOfWeek.FRIDAY  ||
                bill.getCreatedAt().getDayOfWeek() == DayOfWeek.SATURDAY ||
                bill.getCreatedAt().getDayOfWeek() == DayOfWeek.SUNDAY) {
            total = total * 1.05;
        }

        // Independence day discount
        if (bill.getCreatedAt().getMonth() == Month.AUGUST && bill.getCreatedAt().getDayOfMonth() == 24) {
            Discount discount = discountRepo.findByName("Independence day");
            discountAmount = total * discount.getPercent();
            bill.setDiscountPercent(discount.getPercent() * 100);
            bill.setDiscountType(discount.getName());
            bill.setDiscountAmount(Double.parseDouble(formatter.format(discountAmount)));
            total = total - discountAmount;
            orderDto.setHasDiscount(false);
        }

        // Programmer's day discount
        if (bill.getCreatedAt().getDayOfYear() == 256) {
            Discount discount = discountRepo.findByName("Programmer day");
            discountAmount = total * discount.getPercent();
            bill.setDiscountType(discount.getName());
            bill.setDiscountPercent(discount.getPercent() * 100);
            bill.setDiscountAmount(Double.parseDouble(formatter.format(discountAmount)));
            total = total - discountAmount;
            orderDto.setHasDiscount(false);
        }

        // Christmas discount
        if (bill.getCreatedAt().getMonth() == Month.JANUARY && bill.getCreatedAt().getDayOfMonth() == 7) {
            Discount discount = discountRepo.findByName("Christmas");
            discountAmount = total * discount.getPercent();
            bill.setDiscountType(discount.getName());
            bill.setDiscountAmount(Double.parseDouble(formatter.format(discountAmount)));
            bill.setDiscountPercent(discount.getPercent() * 100);
            total = total - discountAmount;
            orderDto.setHasDiscount(false);
        }

        // Regular discount
        if (orderDto.isHasDiscount()) {
            Discount discount = discountRepo.findById(orderDto.getDiscountId()).get();
            discountAmount = total * discount.getPercent();
            bill.setDiscountType(discount.getName());
            bill.setDiscountAmount(Double.parseDouble(formatter.format(discountAmount)));
            bill.setDiscountPercent(discount.getPercent() * 100);
            total = total - discountAmount;
        } else {
            bill.setDiscountType("None");
        }

        if (bill.getCreatedAt().getMonth() == Month.SEPTEMBER &&
                (bill.getCreatedAt().getDayOfWeek() == DayOfWeek.SATURDAY && bill.getCreatedAt().getDayOfMonth() < 8)) {
            tips = 0;
        } else {
            tips = total * 0.05;
        }


        totalWithTips = total + tips;

        bill.setTips(Double.parseDouble(formatter.format(tips)));
        bill.setTotal(Double.parseDouble(formatter.format(totalWithTips)));
        billRepo.save(bill);
        return bill;
    }

    private double countPizzas(OrderDto orderDto, Bill bill) {
        double subtotal = 0;
        double secondPizzaFreeDiscount = 0;
        ArrayList<BillRecord> pizzas = new ArrayList<>();
        for (OrderRecord orderRecord : orderDto.getPizzas()) {
            Pizza orderedPizza = pizzaRepo.findById(orderRecord.getItemId()).get();
            BillRecord billRecord = new BillRecord(orderedPizza.getName(), orderRecord.getAmount());
            billRecordRepo.save(billRecord);
            pizzas.add(billRecord);
            if (orderRecord.getAmount() == 2) {
                subtotal += orderedPizza.getPrice() * (orderRecord.getAmount() / 2);
                secondPizzaFreeDiscount += subtotal;
            }
            subtotal += orderedPizza.getPrice() * orderRecord.getAmount();

        }
        bill.setSecondPizzaFreeDiscount(secondPizzaFreeDiscount);
        bill.setPizzas(pizzas);
        return subtotal;
    }

    private double countDrinks(OrderDto orderDto, Bill bill) {
        double subtotal = 0;
        double coffeeDiscount = 0;
        ArrayList<BillRecord> drinks = new ArrayList<>();

        for (OrderRecord orderRecord : orderDto.getDrinks()) {
            Drink orderedDrink = drinkRepo.findById(orderRecord.getItemId()).get();
            BillRecord billRecord = new BillRecord(orderedDrink.getName(), orderRecord.getAmount());
            billRecordRepo.save(billRecord);
            drinks.add(billRecord);
            if (bill.getCreatedAt().getDayOfWeek() == DayOfWeek.MONDAY) {
                if (orderedDrink.getName().contains("Coffee")) {
                    subtotal += 0;
                    coffeeDiscount += orderedDrink.getPrice() * orderRecord.getAmount();
                }
            } else {
                subtotal += orderedDrink.getPrice() * orderRecord.getAmount();
            }
        }
        bill.setFreeCoffeeDiscount( Double.parseDouble(formatter.format(coffeeDiscount)));
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
