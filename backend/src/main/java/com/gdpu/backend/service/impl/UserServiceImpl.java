package com.gdpu.backend.service.impl;

import com.gdpu.backend.entity.User;
import com.gdpu.backend.mapper.UserMapper;
import com.gdpu.backend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;


    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());

        User existUser = userMapper.selectOne(queryWrapper);
        if (existUser != null) {
            // 用户存在且密码匹配
            String newToken = System.currentTimeMillis() + ""; // 生成新的 token
            existUser.setToken(newToken); // 设置新的 token 到用户对象
            userMapper.updateById(existUser); // 更新数据库中的用户记录
            return existUser; // 返回包含新 token 的用户对象
        } else {
            // 用户不存在或密码不匹配
            return null; // 或者抛出一个异常或者返回一个错误信息
        }
    }

    @Override
    public User getUserByToken(String token) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token", token);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean logout(String token) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("token", token).set("token", null);

        int updated = userMapper.update(null, updateWrapper);
        return updated > 0;
    }
}
