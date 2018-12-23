package com.kadajko.account.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadajko.account.domain.model.Role;
import com.kadajko.account.domain.model.RoleName;
import com.kadajko.account.domain.repository.RoleRepository;
import com.kadajko.account.domain.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleRepository repository;

    @Override
    public Role add(Role role) {
        return repository.add(role);
    }

    @Override
    public Role getRoleByRoleName(RoleName name) {
        return repository.getRoleByRoleName(name);
    }

    @Override
    public List<Role> getAll() {
        return repository.getAll();
    }

}
