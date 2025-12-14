package com.example.Final_WebProject.service;

import com.example.Final_WebProject.entity.FoodData;

import java.util.List;

public interface FoodService {
    List<FoodData> findAll();
    FoodData findById(int id);
    FoodData findByName(String name);
    FoodData save(FoodData foodData);
    void deleteById(int id);
    List<FoodData> searchByName(String keyword);
}
