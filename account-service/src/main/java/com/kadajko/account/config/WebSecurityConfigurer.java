package com.kadajko.account.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        // TODO Auto-generated method stub
//        return super.userDetailsServiceBean();
//    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("khanh")
//                .password("1234")
//                .roles("USER");
        auth.userDetailsService(userDetailsService);
    }
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//        .authorizeRequests()
//            .antMatchers("/login.html", "/logout.do").permitAll()
//            .antMatchers("/**").authenticated()
//        .and()
//        .formLogin()
//            .loginProcessingUrl("/login")
//            .loginPage("/login.html");
//    }
}
