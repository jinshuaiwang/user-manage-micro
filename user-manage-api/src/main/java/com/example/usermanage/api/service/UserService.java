package com.example.usermanage.api.service;

import com.example.usermanage.api.dto.UserDTO;
import com.example.usermanage.api.entity.User;

import java.util.List;

/**
 * Author wangjinshuai
 * Date 2022/9/1 22:19
 **/
public interface UserService {

    /**
     * 保存用户
     * @param
     * @return
     */
    long saveUser(UserDTO userDTO) throws Exception;

    /**
     * 更新
     * @param userDTO
     * @return
     */
    Integer updateUser(UserDTO userDTO) throws Exception;

    /**
     * 查询用户
     * @param userDTO
     * @return
     */
    List<User> getUserByDTO(UserDTO userDTO) throws Exception;

    /**
     * 删除用户
     * @param userId
     * @return
     * @throws Exception
     */
    Integer deleteUser(long userId) throws Exception;
}
