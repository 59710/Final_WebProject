package com.example.Final_WebProject.security;

import com.example.Final_WebProject.entity.UserData;
import com.example.Final_WebProject.entity.UserRole;
import com.example.Final_WebProject.entity.UserRoleRel;
import com.example.Final_WebProject.service.UserRoleRelService;
import com.example.Final_WebProject.service.UserRoleService;
import com.example.Final_WebProject.service.UserService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserService implements UserDetailsService {
    @Resource
    private UserService userService;
    @Resource
    private UserRoleRelService userRoleRelService;
    @Resource
    private UserRoleService userRoleService;

    private Logger logger = LoggerFactory.getLogger(CustomUserService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData user = userService.findByUsername(username);
        if (user == null) {
            logger.info("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
        //获取用户所有关联角色
        List<UserRoleRel> userRoleRelList = userRoleRelService.findByUserId(user.getId());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if(userRoleRelList != null && !userRoleRelList.isEmpty()) {
            for (UserRoleRel userRoleRel : userRoleRelList) {
                //获取用户所有关联角色
                UserRole userRole = userRoleService.find(userRoleRel.getRoleId());
                authorityList.add(new SimpleGrantedAuthority(userRole.getRoleName()));
            }
        }
        return new User(user.getUsername(), user.getPassword(), authorityList);
    }
}
