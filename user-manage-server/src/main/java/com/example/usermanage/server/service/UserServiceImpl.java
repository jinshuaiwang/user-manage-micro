package com.example.usermanage.server.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.usermanage.api.service.UserService;
import com.example.usermanage.api.dto.UserDTO;
import com.example.usermanage.api.entity.User;
import com.example.usermanage.api.enums.UserStatusEnum;
import com.example.usermanage.server.convert.UserConvert;
import com.example.usermanage.server.exception.BizException;
import com.example.usermanage.server.repository.UserRepository;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Author wangjinshuai
 * Date 2022/9/1 22:22
 **/
@Service(interfaceClass = UserService.class)
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserByDTO(UserDTO userDTO) throws Exception {
        return userRepository.getUserByDTO(userDTO);
    }

    @Override
    public long saveUser(UserDTO userDTO) throws Exception {

        Preconditions.checkNotNull(userDTO, "用户信息不能为空");
        Preconditions.checkNotNull(userDTO.getName(), "用户名称不能为空");
        Preconditions.checkNotNull(userDTO.getEmail(), "邮箱不能为空");

        // 构建用户判重条件
        UserDTO queryDTO = UserDTO.UserDTOBuilder.anUserDTO()
                                    .withEmail(userDTO.getEmail())
                                    .build();
        List<User> userList = userRepository.getUserByDTO(queryDTO);

        if (!CollectionUtils.isEmpty(userList)) {
            throw new BizException("用户已存在");
        }

        // 写用户信息
        long userId = userRepository.saveUser(UserConvert.toUser.apply(userDTO));

        // TODO mq 发送邮件，回写发送状态

        return userId;
    }

    public Integer updateUser(UserDTO userDTO) {

        Preconditions.checkNotNull(userDTO, "用户信息不能为空");
        Preconditions.checkNotNull(userDTO.getId(), "用户标示不能为空");

        User user = userRepository.getUserById(userDTO.getId());

        if (user == null) {
            throw new BizException("用户不存在，无法修改");
        }

        if (UserStatusEnum.DELETED.getStatus().equals(user.getUserStatus())) {
            throw new BizException("用户已删除，无法修改");
        }

        UserDTO updateDTO = UserDTO.UserDTOBuilder.anUserDTO()
                .withName(userDTO.getName())
                .withId(userDTO.getId())
                .build();

        return userRepository.updateUser(updateDTO);
    }

    @Override
    public Integer deleteUser(long userId) throws Exception {

        Preconditions.checkState(userId > 0L, "用标示不能小于等于零");

        User user = userRepository.getUserById(userId);

        if (user == null) {
            throw new BizException("用户不存在，无法删除");
        }

        if (UserStatusEnum.DELETED.getStatus().equals(user.getUserStatus())) {
            throw new BizException("用户已删除，无需重复删除");
        }

        UserDTO deleteDTO = UserDTO.UserDTOBuilder.anUserDTO()
                .withUserStatus(UserStatusEnum.DELETED.getStatus())
                .withId(userId)
                .build();

        return userRepository.updateUser(deleteDTO);
    }
}
