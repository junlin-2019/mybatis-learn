package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @Author: admin
 * @Date: 2020/9/29 18:02
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
