package com.example.usermanage.gateway.autoconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Author wangjinshuai
 * Date 2022/9/4 01:39
 **/
@Configuration
@ComponentScan(basePackages = {"com.example.usermanage.gateway", "com.example.usermanage.api"})
@PropertySource("classpath:application.properties")
public class GatewayAutoConfig {

}
