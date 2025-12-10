package com.example.Final_WebProject.service.impl;

import com.example.Final_WebProject.entity.UserRoleRel;
import com.example.Final_WebProject.repository.UserRoleRelRepository;
import com.example.Final_WebProject.service.UserRoleRelService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleRelServiceImpl implements UserRoleRelService {
    @Resource
    private UserRoleRelRepository userRoleRelRepository;

    @Override
    public List<UserRoleRel> findByUserId(int userId) {
        return userRoleRelRepository.findByUserId(userId);
    }
}
