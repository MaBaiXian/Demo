package com.gdpu.backend.service;

import com.gdpu.backend.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    //    根据Token获取最新的三条公告信息
    List<Announcement> getAnnouncementByToken(String token);
}
