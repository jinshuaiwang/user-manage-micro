package com.example.usermanage.gateway;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class UserManageGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(UserManageGatewayApp.class, args);
    }
}
