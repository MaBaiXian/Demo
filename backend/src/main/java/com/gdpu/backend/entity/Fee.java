package com.gdpu.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "fee")
public class Fee {
   @TableId(value = "stuId")
    private String stuId;
    @TableField(value = "stuName")
    private String stuName;
    @TableField(value = "className")
    private String className;
    @TableField(value = "Fee")
    private double Fee;
    @TableField(value = "dormitory")
    private String dormitory;

    @Override
    public String toString() {
        return "Fee{" +
                "stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", className='" + className + '\'' +
                ", Fee=" + Fee +
                ", dormitory='" + dormitory + '\'' +
                '}';
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getFee() {
        return Fee;
    }

    public void setFee(double fee) {
        Fee = fee;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }
}
