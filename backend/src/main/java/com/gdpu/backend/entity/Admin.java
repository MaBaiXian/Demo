package com.gdpu.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//映射数据库表的实体类
@TableName(value = "user")
public class Admin {
    @TableId(value = "userId", type = IdType.AUTO)
    private Integer userId;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "token")
    private String token;
    @TableField(value = "avatar")
    private String avatar;
    @TableField(value = "roles")
    private String roles;
    @TableField(value = "gender")
    private String gender;
    @TableField(value = "dorm")
    private String dorm;
    @TableField(value = "major")
    private String major;

    public Admin() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", avatar='" + avatar + '\'' +
                ", roles='" + roles + '\'' +
                ", gender='" + gender + '\'' +
                ", dorm='" + dorm + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
