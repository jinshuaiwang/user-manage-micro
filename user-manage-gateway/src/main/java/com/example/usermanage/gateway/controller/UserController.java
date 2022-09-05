package com.example.usermanage.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.usermanage.api.service.UserService;
import com.example.usermanage.gateway.convert.UserWebConvert;
import com.example.usermanage.gateway.eunms.WebResultCodeEnum;
import com.example.usermanage.gateway.param.WebResult;
import com.example.usermanage.gateway.param.user.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Author wangjinshuai
 * Date 2022/9/2 21:50
 **/
@RequestMapping("/user")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    private UserService userService;

    @RequestMapping("/register")
    public WebResult registerUser(UserRequest request) {
        try {
            // TODO param validate
            userService.saveUser(UserWebConvert.toUserDTO.apply(request));

            return WebResult.success();
        } catch (Exception e) {
            logger.error("registerUser error request:{}", request, e);
            return WebResult.fail(WebResultCodeEnum.SERVER_INNER_ERROR.getCode(), WebResultCodeEnum.SERVER_INNER_ERROR.getDesc());
        }
    }

    @RequestMapping("/update")
    public WebResult updateUser(UserRequest request) {
        try {
            // TODO param validate
            userService.updateUser(UserWebConvert.toUserDTO.apply(request));

            return WebResult.success();
        } catch (Exception e) {
            logger.error("updateUser error request:{}", request, e);
            return WebResult.fail(WebResultCodeEnum.SERVER_INNER_ERROR.getCode(), WebResultCodeEnum.SERVER_INNER_ERROR.getDesc());
        }
    }

    @RequestMapping("/delete")
    public WebResult deleteUser(@RequestParam(value = "userId") long userId) {
        try {
            // TODO param validate
            userService.deleteUser(userId);

            return WebResult.success();
        } catch (Exception e) {
            logger.error("deleteUser error userId:{}", userId, e);
            return WebResult.fail(WebResultCodeEnum.SERVER_INNER_ERROR.getCode(), WebResultCodeEnum.SERVER_INNER_ERROR.getDesc());
        }
    }
}
