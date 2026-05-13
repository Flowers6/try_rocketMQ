package com.chance.litestock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chance.litestock.mapper")
public class LiteStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiteStockApplication.class, args);
    }

}
