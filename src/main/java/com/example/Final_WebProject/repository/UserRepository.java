package com.example.Final_WebProject.repository;

import com.example.Final_WebProject.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Integer> {

    List<UserData> findAll();

    UserData findByUsername(String username);

    List<UserData> findByEmail(String email);

    List<UserData> findByPhone(String phone);

    List<UserData> findByAddress(String address);

    void deleteAll();

    boolean existsByUsername(String username);

    Optional<UserData> findByUsernameAndPassword(String username, String password);

}
