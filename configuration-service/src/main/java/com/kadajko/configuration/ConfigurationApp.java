package com.kadajko.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationApp {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationApp.class, args);
	}
}
