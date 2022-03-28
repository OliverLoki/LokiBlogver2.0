package com.loki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loki.mapper.UserMapper;
import com.loki.pojo.User;
import com.loki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUsername() {
        List<User> user = userMapper.getUsername();
        return user;
    }
}
