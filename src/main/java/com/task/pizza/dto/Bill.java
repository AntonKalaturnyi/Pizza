package com.task.pizza.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Bill implements Serializable {

    public Bill() {
    }

    public Bill(List<BillRecord> pizzas,
                List<BillRecord> drinks,
                List<BillRecord> extras,
                LocalDateTime createdAt,
                double total) {
        this.pizzas = pizzas;
        this.drinks = drinks;
        this.extras = extras;
        this.createdAt = createdAt;
        this.total = total;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(targetClass = BillRecord.class)
    @CollectionTable(name = "bill_pizza", joinColumns = @JoinColumn(name = "bill_pizza_id"))
    private List<BillRecord> pizzas;

    @ElementCollection(targetClass = BillRecord.class)
    @CollectionTable(name = "bill_drink", joinColumns = @JoinColumn(name = "bill_drink_id"))
    private List<BillRecord> drinks;

    @ElementCollection(targetClass = BillRecord.class)
    @CollectionTable(name = "bill_extra", joinColumns = @JoinColumn(name = "bill_extra_id"))
    private List<BillRecord> extras;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    private double total;

    public List<BillRecord> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<BillRecord> pizzas) {
        this.pizzas = pizzas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<BillRecord> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<BillRecord> drinks) {
        this.drinks = drinks;
    }

    public List<BillRecord> getExtras() {
        return extras;
    }

    public void setExtras(List<BillRecord> extras) {
        this.extras = extras;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
