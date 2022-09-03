package com.example.usermanage.server.convert;

import com.example.usermanage.api.dto.UserDTO;
import com.example.usermanage.api.entity.User;

import java.util.function.Function;

/**
 * Author wangjinshuai
 * Date 2022/9/2 18:00
 **/
public class UserConvert {

    public static Function<UserDTO, User> toUser = userDTO -> {
        if (null == userDTO) {
            return null;
        }

        return User.UserBuilder.anUser()
                .withName(userDTO.getName())
                .withEmail(userDTO.getEmail())
                .build();
    };
}
