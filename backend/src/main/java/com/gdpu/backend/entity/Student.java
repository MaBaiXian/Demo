package com.gdpu.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "user")
public class Student extends User {
    
    public Student() {
        super(); 
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() + "}";
    }
}
