package com.example.Final_WebProject.service.impl;

import com.example.Final_WebProject.entity.UserRole;
import com.example.Final_WebProject.repository.UserRoleRepository;
import com.example.Final_WebProject.service.UserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRole findByRoleId(int id) {
        return userRoleRepository.findById(id).orElse(null);
    }
}
