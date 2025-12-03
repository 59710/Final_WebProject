package com.example.Final_WebProject.controller;


import com.example.Final_WebProject.service.FoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/59710")
public class ProjectController {

    @Resource
    private FoodService foodService;
    @RequestMapping("/MainPage")
    public String mainPage(Model model) {
        model.addAttribute("foodList", foodService.findAll());
        return "MainPage";
    }

    @RequestMapping("/Failed")
    public String failed(Model model) {
        return "Failed";
    }
}
