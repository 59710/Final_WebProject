package com.example.Final_WebProject.repository;

import com.example.Final_WebProject.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserData, Integer> {

    List<UserData> findByUsernameAndPassword(String username, String password);

    List<UserData> findByUsername(String username);

    List<UserData> findByEmail(String email);

    List<UserData> findByPhone(String phone);

    List<UserData> findByAddress(String address);

}
