package com.kadajko.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import com.kadajko.account.domain.model.RoleName;

@Configuration
public class ResourceServerConfiguration 
        extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/v1/account")
                .hasAuthority(RoleName.ROLE_ADMIN.name())
            .antMatchers(HttpMethod.POST, "/v1/account/admin")
                .permitAll()
//                .hasAnyAuthority(RoleName.ROLE_ADMIN.name())
            .antMatchers(HttpMethod.POST, "/v1/account")
                .permitAll()
            .antMatchers(HttpMethod.POST, "/v1/account/logout")
                .authenticated()
            .antMatchers(HttpMethod.PUT, "/v1/account/**")
                .authenticated();
//        .antMatchers(HttpMethod.DELETE, "/v1/organizations/**")
//        .hasRole("ADMIN")
//        .anyRequest()
//        The hasRole() method is a
//        .authenticated();
    }
}
