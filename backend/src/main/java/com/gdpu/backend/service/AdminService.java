package com.gdpu.backend.service;

import com.gdpu.backend.entity.Admin;

import java.util.List;

public interface AdminService {
    // 更新管理员信息
    int updateAdmin(String token, int id, Admin admin);

    // 通过token验证身份返回管理员列表
    List<Admin> getAdminByToken(String token);

    // 删除管理员
    int deleteAdmin(String token, int id);

    // 创建管理员
    int createAdmin(String token, Admin admin);
}
