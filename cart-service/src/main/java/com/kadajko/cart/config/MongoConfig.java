package com.kadajko.cart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.kadajko.cart.domain.repository"})
public class MongoConfig {

}
