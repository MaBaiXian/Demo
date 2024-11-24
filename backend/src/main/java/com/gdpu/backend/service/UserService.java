package com.gdpu.backend.service;

import com.gdpu.backend.entity.User;

public interface UserService {

    //    用户登录
    User login(User user);

    //    通过token获取用户信息
    User getUserByToken(String token);

    //    登出功能
    boolean logout(String token);
}
