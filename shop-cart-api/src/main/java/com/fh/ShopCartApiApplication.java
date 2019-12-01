package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class ShopCartApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopCartApiApplication.class, args);
    }

}
