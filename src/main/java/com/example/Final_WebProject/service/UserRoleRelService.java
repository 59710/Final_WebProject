package com.example.Final_WebProject.service;

import com.example.Final_WebProject.entity.UserRoleRel;

import java.util.List;

public interface UserRoleRelService {
    List<UserRoleRel> findByUserId(int userId);
}
