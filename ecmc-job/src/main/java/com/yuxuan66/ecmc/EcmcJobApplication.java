package com.yuxuan66.ecmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Sir丶雨轩
 * @since 2022/12/22
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
public class EcmcJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcmcJobApplication.class, args);
    }
}
