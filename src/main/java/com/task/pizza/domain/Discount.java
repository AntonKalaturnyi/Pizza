package com.task.pizza.domain;

import javax.persistence.*;

@Entity
public class Discount {

    public Discount() {
    }

    public Discount(String name, double percent) {
        this.name = name;
        this.percent = percent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "percent", nullable = false)
    private double percent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
