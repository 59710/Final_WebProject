package com.example.Final_WebProject.service.impl;

import com.example.Final_WebProject.entity.UserData;
import com.example.Final_WebProject.repository.UserRepository;
import com.example.Final_WebProject.service.UserService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Override
    public boolean login(String username, String password) {
        if (username.equals("admin") && password.equals("<PASSWORD>")) {
            return true;
        }
        else if (username.equals("user") && password.equals("<PASSWORD>")) {
            return true;
        }
        return false;
    }

    @Override
    public UserData findByUsername(String username) {
        List<UserData> userData = findAll();
        for (UserData user : userData) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<UserData> findAll() {
        List<UserData> userData = new ArrayList<>();
        return null;
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
}
