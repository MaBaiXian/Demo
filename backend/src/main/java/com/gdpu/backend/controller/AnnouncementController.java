package com.gdpu.backend.controller;

import com.gdpu.backend.dto.Result;
import com.gdpu.backend.entity.Announcement;
import com.gdpu.backend.service.AnnouncementService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/Announcement/getAllAnnouncements")
    public Result getAllAnnouncements(@RequestHeader("X-Token") String token) {
        try {
            List<Announcement> announcements = announcementService.getAllAnnouncement(token);
            if (Objects.nonNull(announcements)) {
                return new Result(announcements);
            } else {
                return new Result();
            }
        } catch (RuntimeException e) {
            return new Result(50008, "token验证失败");
        }
    }

    @DeleteMapping(value = "/Announcement/deleteAnnouncement/{id}")
    public Result deleteAnnouncement(@RequestHeader("X-Token") String token, @PathVariable int id) {
        try {
            // 调用服务层的方法删除公告，传递 token 和公告 id
            int result = announcementService.deleteAnnouncement(token, id);
            // 根据 result 的值返回不同的结果
            if (result > 0) {
                // 如果 result 大于 0，表示删除成功
                return new Result(result);
            } else {
                // 如果 result 为 0，表示没有找到对应的公告进行删除
                return new Result(-1, "未找到对应的公告");
            }
        } catch (RuntimeException e) {
            // 捕获服务层抛出的异常，并返回相应的错误信息
            return new Result(-1, e.getMessage());
        }
    }

    @PutMapping("/Announcement/updateAnnouncement/{id}")
    public Result updateAnnouncement(@RequestHeader("X-Token") String token, @PathVariable int id, @RequestBody Announcement announcement) {
        try {
            // 调用服务层的方法更新公告，传递 token、公告 id 和公告数据
            int result = announcementService.updateAnnouncement(token, id, announcement.getMessage());
            // 根据 result 的值返回不同的结果
            if (result > 0) {
                // 如果 result 大于 0，表示更新成功
                return new Result(result);
            } else {
                // 如果 result 为 0，表示没有找到对应的公告进行更新
                return new Result(-1, "未找到对应的公告");
            }
        } catch (RuntimeException e) {
            // 捕获服务层抛出的异常，并返回相应的错误信息
            return new Result(-1, e.getMessage());
        }
    }

    @PostMapping("/Announcement/createAnnouncement")
    public Result createAnnouncement(@RequestHeader("X-Token") String token, @RequestBody Announcement announcement) {
        try {
            // 调用服务层的方法创建公告，传递 token 和公告数据
            int result = announcementService.createAnnouncement(token, announcement.getMessage());
            // 根据 result 的值返回不同的结果
            if (result > 0) {
                // 如果 result 大于 0，表示创建成功
                return new Result(result);
            } else {
                // 如果 result 为 0，表示创建失败
                return new Result(-1, "创建公告失败");
            }
        } catch (RuntimeException e) {
            // 捕获服务层抛出的异常，并返回相应的错误信息
            return new Result(-1, e.getMessage());
        }
    }

}

