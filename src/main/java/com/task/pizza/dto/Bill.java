package com.task.pizza.dto;

import com.task.pizza.domain.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Bill implements Serializable {

    public Bill() {
    }

    public Bill(List<BillRecord> pizzas, double total) {
        this.pizzas = pizzas;
        this.total = total;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(targetClass = BillRecord.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "bill_pizza", joinColumns = @JoinColumn(name = "bill_pizza_id"))
    private List<BillRecord> pizzas;

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
}
