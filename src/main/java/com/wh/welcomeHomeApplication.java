//package com.wh;
//
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
package com.wh;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
@MapperScan("com.wh.mapper")
@SpringBootApplication
public class welcomeHomeApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource; // 数据源对象，用于测试数据库连接

    public static void main(String[] args) {
        SpringApplication.run(welcomeHomeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Testing database connection...");
        System.out.println("DataSource: " + dataSource.getConnection());
        System.out.println("Database connected successfully!");
    }
}

//@MapperScan("com.wh.mapper")
//@SpringBootApplication
//public class welcomeHomeApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(welcomeHomeApplication.class, args);
//    }
//
//}
