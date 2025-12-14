package com.example.Final_WebProject.controller;


import com.example.Final_WebProject.email.SendSubscribeEmail;
import com.example.Final_WebProject.entity.FoodData;
import com.example.Final_WebProject.entity.UserData;
import com.example.Final_WebProject.entity.UserRole;
import com.example.Final_WebProject.entity.UserRoleRel;
import com.example.Final_WebProject.service.FoodService;
import com.example.Final_WebProject.service.UserRoleRelService;
import com.example.Final_WebProject.service.UserRoleService;
import com.example.Final_WebProject.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    private UserService userService;

    @Resource
    private FoodService foodService;
    @Resource
    private UserRoleRelService userRoleRelService;
    @Resource
    private UserRoleService userRoleService;

    @Autowired
    private SendSubscribeEmail sendSubscribeEmail;

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    // 跳转到登录页面
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login-page")
    public String loginPage() {
        // 应该直接返回视图名，不是重定向
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // 处理登录请求
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Optional<UserData> user = userService.login(username, password);


        if (user.isPresent()) {
            // 登录成功，保存用户信息到session
            session.setAttribute("user", user.get());
            List<UserRoleRel> userRoleRelList = userRoleRelService.findByUserId(user.get().getId());
            if(userRoleRelList != null && !userRoleRelList.isEmpty()) {
                for (UserRoleRel userRoleRel : userRoleRelList) {
                    if (userRoleService.findByRoleId(userRoleRel.getRoleId()).getRoleName()==null){
                        System.out.println("用户权限为空！");
                    }
                    else {
                        System.out.println(user.get().getUsername()+"的权限为:");
                        System.out.println(userRoleService.findByRoleId(userRoleRel.getRoleId()).getRoleName());
                    }
                }
            }
            logger.info("{}已登录", user.get().getUsername());
            return "redirect:/home";
        }
        else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    // 跳转到注册页面
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // 处理注册请求
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           @RequestParam String address,
                           Model model) {
        try {
            UserData user = new UserData();
            user.setUsername(username);
            // 实际项目中应该加密存储
            user.setPassword(password);
            user.setEmail(email);
            user.setAddress(address);
            userService.register(user);
            model.addAttribute("success", "注册成功，请登录");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    // 主页

    @GetMapping("/home")
    public String home(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        UserData user = (UserData) session.getAttribute("user");
        if (user == null) {
            System.out.println("请先登录!");
            redirectAttributes.addFlashAttribute("error", "请先登录");
            return "redirect:/login";
        }
        model.addAttribute("foodList", foodService.findAll());
        foodService.findAll().forEach(food -> {
            System.out.println(food.getName());
        });
        return "MainPage";
    }

    @PostMapping("/home/order")
    public String home(@RequestBody Map<String, Integer> requestBody,
                       HttpSession session, Model model,
                       RedirectAttributes redirectAttributes) {

        logger.info("@PostMapping(\"/home/order\")" + "public String home is Ready!");
        UserData user = (UserData) session.getAttribute("user");
        FoodData food = foodService.findById(requestBody.get("foodId"));
        logger.info("{}用户正在预定：{}", user.getUsername(),food.getName());
        if (user != null) {
            System.out.println(user.getUsername()+"正在预定！");
            redirectAttributes.addFlashAttribute("message", user.getUsername() + "正在预定！");
        }
        else {
            redirectAttributes.addFlashAttribute("error", "用户不存在！");
            System.out.println("用户不存在！");
        }
        if (food != null) {
            redirectAttributes.addFlashAttribute("success", "预定成功！邮件已发送。");
            sendSubscribeEmail.sendEmail(user, food);
        }
        else {
            redirectAttributes.addFlashAttribute("error", "菜品不存在！");
            System.out.println("菜品不存在！");
            return "redirect:/home";
        }
        return "redirect:/home";
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


    @RequestMapping("/Failed")
    public String failed(Model model) {
        return "Failed";
    }

    @GetMapping("/search")
    public String searchFood(@RequestParam(value = "keyword", required = false) String keyword,
                             HttpSession session,
                             Model model) {
        UserData user = (UserData) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<FoodData> foodList;

        if (keyword == null || keyword.trim().isEmpty()) {
            // 如果没有关键词，显示所有菜品
            foodList = foodService.findAll();
            model.addAttribute("message", "所有菜品");
        } else {
            // 执行搜索
            foodList = foodService.searchByName(keyword.trim());
            model.addAttribute("keyword", keyword);
            model.addAttribute("message", "搜索结果: " + keyword);
        }

        model.addAttribute("foodList", foodList);
        return "SearchPage"; // 创建一个简单的搜索结果页面
    }
}
