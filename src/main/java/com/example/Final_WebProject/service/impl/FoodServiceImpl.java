package com.example.Final_WebProject.service.impl;

import com.example.Final_WebProject.entity.FoodData;
import com.example.Final_WebProject.repository.FoodRepository;
import com.example.Final_WebProject.service.FoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Resource(name = "foodRepository")
    private FoodRepository foodRepository;
    @Override
    public FoodData findById(int id) {
        return foodRepository.findById(id);
    }

    @Override
    public List<FoodData> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public FoodData save(FoodData foodData){
        return foodRepository.save(foodData);
    }

    @Override
    public void deleteById(int id) {
        foodRepository.deleteById(id);
    }

    @Override
    public FoodData findByName(String name) {
        return foodRepository.findByName(name);
    }

    @Override
    public List<FoodData> searchByName(String keyword) {
        return foodRepository.searchByName(keyword);
    }
}
