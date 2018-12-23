package com.kadajko.account.domain.repository;

import java.util.UUID;

import com.kadajko.account.domain.model.User;

public interface UserRepository extends BaseRepository<User, UUID> {
    User loadUserByEmail(String email);
    
    boolean containsEmail(String email);
    
    void changePassword(String email, String newPassword);
}
