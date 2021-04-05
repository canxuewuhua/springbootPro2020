package com.example.demo.dailyexercise.red;

import lombok.Data;

@Data
public class People {

    private String name;

    private double amount;

    public People(String name, double amount){
        this.name = name;
        this.amount = amount;
    }
}
