package com.kadajko.account.resource;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kadajko.account.domain.model.Role;
import com.kadajko.account.domain.model.RoleName;
import com.kadajko.account.domain.model.User;
import com.kadajko.account.domain.service.RoleService;
import com.kadajko.account.domain.service.UserService;
import com.kadajko.account.exception.EmailExistsException;

@RestController
@RequestMapping("/v1/account")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    Logger logger = Logger.getLogger(UserController.class.getName());

//    @GetMapping("/{email:.+}")
//    public User getUserByEmail(@PathVariable String email) {
//        logger.log(Level.INFO, "email = " + email);
//        return userService.loadUserByEmail(email);
//    }

    @GetMapping
    public List<User> getAll() {
//        logger.log(Level.INFO, "email = " + email);
        return userService.getAll();
    }
    
    @GetMapping("/{email:.+}")
    public User getUserByEmail(@PathVariable String email) {
    	return userService.loadUserByEmail(email);
    }
    
    @Autowired
    ConsumerTokenServices tokenServices;

    @PostMapping("/logout")
    public void revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String token = authorization.substring("Bearer".length() + 1);
            tokenServices.revokeToken(token);
        }
    }

    @PostMapping
    public ResponseEntity<User> registerForUser(@RequestBody User user) {
        if (userService.containsEmail(user.getEmail()))
            throw new EmailExistsException(
                    "Email '" + user.getEmail() + "' was existed");

        Role userRole = roleService.getRoleByRoleName(RoleName.ROLE_USER);
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setCreatedAt(new Date());
        
        User newUser = userService.add(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/v1/account/")
                .path(newUser.getId().toString())
                .build().toUri());

        return new ResponseEntity<User>(newUser, headers, HttpStatus.CREATED);
    }
    
    @PostMapping("/admin")
    public ResponseEntity<User> addUserForAdmin(@RequestBody User user) {
        if (userService.containsEmail(user.getEmail()))
            throw new EmailExistsException(
                    "Email '" + user.getEmail() + "' was existed");

        Role userRole = roleService.getRoleByRoleName(RoleName.ROLE_ADMIN);
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setCreatedAt(new Date());
        
        User newUser = userService.add(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/v1/account/")
                .path(newUser.getId().toString())
                .build().toUri());

        return new ResponseEntity<User>(newUser, headers, HttpStatus.CREATED);
    }

    @PutMapping("/password")
    public void changePassword(Principal user, 
            @RequestParam("password") String newPassword, HttpServletRequest request) {
        userService.changePassword(user.getName(), newPassword);
        this.revokeToken(request);
    }
    
    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * Using by protected service to authenticating token from user. It will be
     * received token from protected service and return user detail of token. The
     * endpoint is mapped to /user
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = { "/user" }, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }

    /**
     * Get user info from token
     * @param user
     * @return
     */
    @GetMapping("/info")
    public Principal user(Principal user) {
        return user;
    }
}
