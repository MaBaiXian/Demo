package com.gdpu.backend.service;

import com.gdpu.backend.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    //    根据Token获取最新的三条公告信息
    List<Announcement> getAnnouncementByToken(String token);

    //    根据Token获取所有公告信息
    List<Announcement> getAllAnnouncement(String token);

    int deleteAnnouncement(String token,int id);

}
