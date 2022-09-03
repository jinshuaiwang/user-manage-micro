package com.example.usermanage.server.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import com.example.usermanage.api.service.HelloService;

@Service(interfaceClass = HelloService.class)
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String seyHello(String name) {
        return "Hello " + name + " , this is provider";
    }
}
