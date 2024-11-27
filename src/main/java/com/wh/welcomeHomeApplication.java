package com.wh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wh.mapper")
@SpringBootApplication
public class welcomeHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(welcomeHomeApplication.class, args);
    }

}
