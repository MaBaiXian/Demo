package com.gdpu.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "user")
public class Admin extends User {
    
    public Admin() {
        super(); 
    }

    @Override
    public String toString() {
        return "Admin{" + super.toString() + "}";
    }
}
