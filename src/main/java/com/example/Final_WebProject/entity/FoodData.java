package com.example.Final_WebProject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.awt.*;


@Entity
@Table(name = "food_data")
public class FoodData {
    @Id
    private int id;

    private String foodImageStr;
    private String name;
    private double price;
    private String description;

    public FoodData() {
    }

    public FoodData(int id, String foodImageStr, String name, double price, String description) {
        this.id = id;
        this.foodImageStr = foodImageStr;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getFoodImageStr() {
        return foodImageStr;
    }

    public void setFoodImageStr(String foodImageStr) {
        this.foodImageStr = foodImageStr;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
