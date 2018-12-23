package com.kadajko.account.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kadajko.account.domain.model.User;
import com.kadajko.account.domain.repository.UserRepository;
import com.kadajko.account.domain.service.UserService;

@Service
@Primary
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository repository;
    
    Logger logger = Logger.getLogger(UserService.class.getName());

    @Override
    public User add(User user) {
        return repository.add(user);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean containsEmail(String email) {
        return repository.containsEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) 
            throws UsernameNotFoundException {
        User user = repository.loadUserByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException(
                    "User '" + email + "' not found.");
            
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        });
//        logger.warning("Reached loadUserByUsername end");
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public void changePassword(String email, String newPassword) {
        repository.changePassword(email, newPassword);
    }

    @Override
    public User update(User user) {
        return repository.update(user);
    }

    
}
