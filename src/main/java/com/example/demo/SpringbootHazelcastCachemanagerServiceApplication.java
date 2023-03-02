package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages = {"com.example.demo"})
@EnableCaching
@Slf4j
public class SpringbootHazelcastCachemanagerServiceApplication {

	@Autowired
    private CacheManager cacheManager;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootHazelcastCachemanagerServiceApplication.class, args);
		log.info("Application started to load data into cache!");
	}
	
}
