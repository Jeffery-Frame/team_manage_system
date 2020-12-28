package com.wanxi.springboot.team.manage.system.service;

import com.wanxi.springboot.team.manage.system.model.Permission;
import com.wanxi.springboot.team.manage.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= (User) userService.getUserByName(username).getData();
        List<Permission> permissionList= userService.getPermissionsByUserId(user.getId());
        HashSet<Permission> permissions = new HashSet<>(permissionList);
        user.setAuthorities(permissions);
        return user;
    }
}