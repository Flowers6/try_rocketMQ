package com.chance.litestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.chance.litestock")
public class LiteStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiteStockApplication.class, args);
    }

}
