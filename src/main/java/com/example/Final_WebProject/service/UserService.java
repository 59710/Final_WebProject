package com.example.Final_WebProject.service;

import com.example.Final_WebProject.entity.UserData;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UserData register(UserData user);
    public Optional<UserData> login(String username, String password);
    UserData findByUsername(String username);
    List<UserData> findAll();
    UserData save(UserData userData);
    void deleteAll();
    void deleteById(int id);
    UserData findById(int userid);
}
