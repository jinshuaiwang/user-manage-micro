package com.example.usermanage.gateway.convert;

import com.example.usermanage.api.dto.UserDTO;
import com.example.usermanage.gateway.param.user.UserRequest;

import java.util.function.Function;

/**
 * Author wangjinshuai
 * Date 2022/9/2 22:51
 **/
public class UserWebConvert {

    public static Function<UserRequest, UserDTO> toUserDTO = request -> {

        if (null == request) {
            return null;
        }

        return UserDTO.UserDTOBuilder.anUserDTO()
                .withId(request.getId())
                .withEmail(request.getEmail())
                .withName(request.getName())
                .withUserStatus(request.getUserStatus())
                .build();
    };
}
