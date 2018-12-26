package com.kadajko.account.domain.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kadajko.account.domain.model.User;

public interface UserService extends UserDetailsService {
    User add(User user);
    
    List<User> getAll();
    
    boolean containsEmail(String email);
    
    void changePassword(String email, String newPassword);
    
    User update(User user);
    
    User loadUserByEmail(String email);
}
