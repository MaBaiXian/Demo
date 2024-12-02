package com.gdpu.backend.service;

import com.gdpu.backend.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    //    根据Token获取最新的三条公告信息
    List<Announcement> getAnnouncementByToken(String token);

    //    根据Token获取所有公告信息
    List<Announcement> getAllAnnouncement(String token);

    // 根据公告id删除公告
    int deleteAnnouncement(String token, int id);

    // 根据公告id更新公告
    int updateAnnouncement(String token, int id, String message);

    // 通过token创建公告
    int createAnnouncement(String token, String message);
}
