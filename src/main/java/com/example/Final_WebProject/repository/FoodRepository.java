package com.example.Final_WebProject.repository;

import com.example.Final_WebProject.entity.FoodData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodData, Integer> {

    FoodData findByName(String name);
    List<FoodData> findByDescription(String description);
    List<FoodData> findByPrice(double price);
    FoodData findById(int id);
    @Query("SELECT f FROM FoodData f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<FoodData> searchByName(@Param("keyword") String keyword);
}
