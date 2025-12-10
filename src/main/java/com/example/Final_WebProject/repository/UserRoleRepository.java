package com.example.Final_WebProject.repository;

import com.example.Final_WebProject.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByRoleId(@Param("roleId")int roleId);
}
