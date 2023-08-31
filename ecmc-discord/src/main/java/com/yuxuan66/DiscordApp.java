package com.yuxuan66;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DiscordApp {
    public static void main(String[] args) {
        SpringApplication.run(DiscordApp.class, args);
    }
}