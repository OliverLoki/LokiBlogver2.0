package com.loki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loki.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    List<User> getUsername();
}
