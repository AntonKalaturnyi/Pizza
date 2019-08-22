package com.task.pizza.dto;

import java.util.List;

public class OrderDto {

    public OrderDto() {
    }

    public OrderDto(List<OrderRecord> pizzas) {
        this.pizzas = pizzas;
    }

    private List<OrderRecord> pizzas;

    public List<OrderRecord> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<OrderRecord> pizzas) {
        this.pizzas = pizzas;
    }
}
