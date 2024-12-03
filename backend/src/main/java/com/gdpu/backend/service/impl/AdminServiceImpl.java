package com.gdpu.backend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdpu.backend.entity.Admin;
import com.gdpu.backend.entity.User;
import com.gdpu.backend.mapper.AdminMapper;
import com.gdpu.backend.service.AdminService;
import com.gdpu.backend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;
    @Resource
    private UserService userService;

    @Override
    public int updateAdmin(String token, int id, Admin admin) {
        // 根据token获取用户信息
        User user = userService.getUserByToken(token);

        if (user == null || (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin"))) {
            throw new RuntimeException("无效的Token或权限不足");
        }

        // 检查管理员是否存在
        Admin existingAdmin = adminMapper.selectById(id);
        if (existingAdmin == null) {
            return 0; // 如果不存在，返回0
        }
        // 执行更新操作
        return adminMapper.updateById(admin);
    }

    @Override
    public List<Admin> getAdminByToken(String token) {
        // 根据token获取用户信息
        // 这里假设userService.getUserByToken(token)方法返回null时表示无效的token
        // 并且只有SysAdmin和DormAdmin角色可以获取管理员列表
        User user = userService.getUserByToken(token);

        if (user == null || (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin"))) {
            throw new RuntimeException("无效的Token或权限不足");
        }

        // 使用QueryWrapper构建查询条件
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("roles", "SysAdmin", "DormAdmin"); // 筛选roles为SysAdmin或DormAdmin的管理员

        // 执行查询
        return adminMapper.selectList(queryWrapper);
    }


    @Override
    public int deleteAdmin(String token, int id) {
        // 根据token获取用户信息
        User user = userService.getUserByToken(token);

        if (user == null || (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin"))) {
            throw new RuntimeException("无效的Token或权限不足");
        }

        // 检查管理员是否存在
        Admin admin = adminMapper.selectById(id);
        if (admin == null) {
            return 0; // 如果不存在，返回0
        }
        // 执行删除操作
        return adminMapper.deleteById(id);
    }

    @Override
    public int createAdmin(String token, Admin admin) {
        // 根据token获取用户信息
        User user = userService.getUserByToken(token);

        if (user == null || (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin"))) {
            throw new RuntimeException("无效的Token或权限不足");
        }

        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", admin.getUsername());
        Long existingAdminCount = adminMapper.selectCount(queryWrapper);
        if (existingAdminCount > 0) {
            // 如果已存在相同用户名的管理员，抛出异常或返回特定值
            throw new RuntimeException("管理员用户名已存在");
        }
        // 执行创建操作
        return adminMapper.insert(admin);
    }

}
