package com.kadajko.account.domain.service;

import java.util.List;

import com.kadajko.account.domain.model.Role;
import com.kadajko.account.domain.model.RoleName;

public interface RoleService {
    Role add(Role role);
    Role getRoleByRoleName(RoleName name);
    List<Role> getAll();
}
