package com.gdpu.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
@TableName(value = "repair")
public class Repair {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "title")
    private String title;
    @TableField(value = "applicant")
    private Integer applicant;
    @TableField(value = "application_time")
    private Date applicationTime;
    @TableField(value = "importance")
    private Integer importance;
    @TableField(value = "content")
    private String content;
    @TableField(value = "image_uri")
    private String imageUri;
    @TableField(value = "status")
    private Integer status;
}
