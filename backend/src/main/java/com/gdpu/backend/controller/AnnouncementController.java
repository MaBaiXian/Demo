package com.gdpu.backend.controller;

import com.gdpu.backend.dto.Result;
import com.gdpu.backend.entity.Announcement;
import com.gdpu.backend.service.AnnouncementService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
public class AnnouncementController {
    @Resource
    private AnnouncementService announcementService;

    @GetMapping(value = "/profile/getAnnouncements")
    public Result getAnnouncements(@RequestHeader("X-Token") String token) {
        try {
            List<Announcement> announcements = announcementService.getAnnouncementByToken(token);
            if (Objects.nonNull(announcements)) {
                return new Result(announcements);
            } else {
                return new Result();
            }
        } catch (RuntimeException e) {
            return new Result(50008, "token验证失败");
        }
    }
}
