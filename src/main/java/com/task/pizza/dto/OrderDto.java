package com.task.pizza.dto;

import java.util.List;

public class OrderDto {

    public OrderDto() {
    }

    public OrderDto(boolean hasPizza,
                    boolean hasDrink,
                    boolean hasExtras,
                    List<OrderRecord> pizzas,
                    List<OrderRecord> drinks,
                    List<OrderRecord> extras) {
        this.hasPizza = hasPizza;
        this.hasDrink = hasDrink;
        this.hasExtras = hasExtras;
        this.pizzas = pizzas;
        this.drinks = drinks;
        this.extras = extras;
    }

    private boolean hasPizza;

    private boolean hasDrink;

    private boolean hasExtras;

    private List<OrderRecord> pizzas;

    private List<OrderRecord> drinks;

    private List<OrderRecord> extras;

    public List<OrderRecord> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<OrderRecord> pizzas) {
        this.pizzas = pizzas;
    }

    public List<OrderRecord> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<OrderRecord> drinks) {
        this.drinks = drinks;
    }

    public boolean isHasPizza() {
        return hasPizza;
    }

    public void setHasPizza(boolean hasPizza) {
        this.hasPizza = hasPizza;
    }

    public boolean isHasDrink() {
        return hasDrink;
    }

    public void setHasDrink(boolean hasDrink) {
        this.hasDrink = hasDrink;
    }

    public boolean isHasExtras() {
        return hasExtras;
    }

    public void setHasExtras(boolean hasExtras) {
        this.hasExtras = hasExtras;
    }

    public List<OrderRecord> getExtras() {
        return extras;
    }

    public void setExtras(List<OrderRecord> extras) {
        this.extras = extras;
    }
}
