package com.gdpu.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "announcement")
public class Announcement {

    @TableId(value = "anncId", type = IdType.AUTO)
    private int anncId;

    @TableField(value = "publisher")
    private String publisher;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "message")
    private String message;

    @TableField(value = "time")
    private String time;

    public Announcement() {
    }

    public int getId() {
        return anncId;
    }

    public void setId(int anncId) {
        this.anncId = anncId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "anncId=" + anncId +
                ", publisher='" + publisher + '\'' +
                ", avatar='" + avatar + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
