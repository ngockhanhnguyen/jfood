package com.kadajko.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class AccountApp {

    public static void main(String[] args) {
        SpringApplication.run(AccountApp.class, args);
    }
}
