package com.example.Final_WebProject.service;

import com.example.Final_WebProject.entity.UserData;

import java.util.List;

public interface UserService {
    UserData findByUsername(String username);
    List<UserData> findAll();
    boolean login(String username, String password);
    UserData save(UserData userData);
    void deleteById(int id);
}
