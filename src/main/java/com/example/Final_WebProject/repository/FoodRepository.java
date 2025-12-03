package com.example.Final_WebProject.repository;

import com.example.Final_WebProject.entity.FoodData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodData, Integer> {

    List<FoodData> findByName(String name);
    List<FoodData> findByDescription(String description);
    List<FoodData> findByPrice(double price);
    FoodData findById(int id);

}
