package com.kadajko.account.domain.repository;

import java.util.UUID;

import com.kadajko.account.domain.model.Role;
import com.kadajko.account.domain.model.RoleName;

public interface RoleRepository extends BaseRepository<Role, UUID> {
    Role getRoleByRoleName(RoleName name);
}
