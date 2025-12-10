package com.example.Final_WebProject.repository;

import com.example.Final_WebProject.entity.UserRoleRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRelRepository extends JpaRepository<UserRoleRel, Integer> {

    List<UserRoleRel> findByUserId(@Param("userId")int userId);
    List<UserRoleRel> findByRoleId(@Param("roleId")int roleId);
}
