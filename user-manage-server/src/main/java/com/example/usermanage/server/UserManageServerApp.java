package com.example.usermanage.server;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class UserManageServerApp {

    public static void main(String[] args) {
        SpringApplication.run(UserManageServerApp.class, args);
    }
}
