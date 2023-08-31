package com.yuxuan66.ecmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Sir丶雨轩
 * @since 2022/12/9
 */
@EnableCaching
@SpringBootApplication
public class EcmcApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcmcApplication.class, args);
    }
}
