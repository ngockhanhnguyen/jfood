package com.kadajko.account.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kadajko.account.domain.model.Role;
import com.kadajko.account.domain.model.RoleName;
import com.kadajko.account.domain.service.RoleService;

@RestController
@RequestMapping("/v1/roles")
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    
    @GetMapping
    public List<Role> getAll() {
        return roleService.getAll();
    }
    
    @GetMapping("/admin")
    public Role getByRoleName() {
        return roleService.getRoleByRoleName(RoleName.ROLE_ADMIN);
    }
    
    @PostMapping
    public Role add(@RequestBody Role role) {
        return roleService.add(role);
    }
    
    
}
