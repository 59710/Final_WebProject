package com.example.Final_WebProject;

import com.example.Final_WebProject.entity.FoodData;
import com.example.Final_WebProject.service.FoodService;
import com.example.Final_WebProject.service.impl.FoodServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FinalWebProjectApplicationTests {

    @Resource
    private FoodService foodService;

    @Test
    void DataLoads() {
        FoodData food1Data = new FoodData(1, "src/main/resources/static/images/food(1).jpg", "麻婆豆腐", 10.99, "tasted good!1");
        FoodData food2Data = new FoodData(2, "src/main/resources/static/images/food(2).jpg", "Niko", 12.99, "tasted good!2");
        FoodData food3Data = new FoodData(3, "src/main/resources/static/images/food(3).jpg", "金叶白菜", 12.99, "tasted good!3");
        foodService.save(food3Data);
        foodService.save(food2Data);
        foodService.save(food1Data);
        System.out.println("FoodData Loading have done!");
    }
	@Test
	void contextLoads() {
	}

}
