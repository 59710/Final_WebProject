package com.example.Final_WebProject.service.impl;

import com.example.Final_WebProject.entity.UserData;
import com.example.Final_WebProject.repository.UserRepository;
import com.example.Final_WebProject.service.UserService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Override
    public UserData findById(int id) {
        Optional<UserData> userData = userRepository.findById(id);
        return userData.orElse(null);
    }

    @Override
    public UserData findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
    @Override
    public List<UserData> findAll() {
        List<UserData> userDataList = userRepository.findAll();
        return userDataList;
    }

    @Transactional
    @Override
    public UserData save(UserData userData) {
        return userRepository.save(userData);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll(){
        userRepository.deleteAll();
    };

    // 注册用户
    @Override
    public UserData register(UserData user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        return userRepository.save(user);
    }

    // 登录验证
    @Override
    public Optional<UserData> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
