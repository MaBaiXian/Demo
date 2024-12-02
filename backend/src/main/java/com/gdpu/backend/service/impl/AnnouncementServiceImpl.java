package com.gdpu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdpu.backend.entity.Announcement;
import com.gdpu.backend.entity.User;
import com.gdpu.backend.mapper.AnnouncementMapper;
import com.gdpu.backend.service.AnnouncementService;
import com.gdpu.backend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Resource
    AnnouncementMapper announcementMapper;
    @Resource
    UserService userService;

    @Override
    public List<Announcement> getAnnouncementByToken(String token) {
        User user = userService.getUserByToken(token);

        if (user == null) {
            throw new RuntimeException("无效的Token");
        } else {
            QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
            queryWrapper.last("ORDER BY time DESC LIMIT 3");
            return announcementMapper.selectList(queryWrapper);
        }
    }

    @Override
    public List<Announcement> getAllAnnouncement(String token) {
        User user = userService.getUserByToken(token);

        if (user == null) {
            throw new RuntimeException("无效的Token");
        } else {
            QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
            return announcementMapper.selectList(queryWrapper);
        }
    }

    @Override
    public int deleteAnnouncement(String token, int id) {
        User user = userService.getUserByToken(token);
        // 检查用户是否为空
        if (user == null) {
            throw new RuntimeException("无效的Token");
        }

        // 检查用户是否有足够权限
        if (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin")) {
            throw new RuntimeException("权限不足");
        }

        // 检查公告是否存在
        Announcement announcement = announcementMapper.selectById(id);
        if (announcement == null) {
            // 如果公告不存在，返回 0 表示没有删除任何行
            return 0;
        }
        // 执行删除操作
        return announcementMapper.deleteById(id);
    }

    @Override
    public int updateAnnouncement(String token, int id, String message) {
        User user = userService.getUserByToken(token);
        // 检查用户是否为空
        if (user == null) {
            throw new RuntimeException("无效的Token");
        }
        // 检查用户是否有足够权限
        if (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin")) {
            throw new RuntimeException("权限不足");
        }
        // 检查公告是否存在
        Announcement ann = announcementMapper.selectById(id);
        if (ann == null) {
            // 如果公告不存在，返回 0 表示没有删除任何行
            return 0;
        }
        // 执行更新操作
        ann.setMessage(message);
        return announcementMapper.updateById(ann);
    }

    @Override
    public int createAnnouncement(String token, String message) {
        User user = userService.getUserByToken(token);
        // 检查用户是否为空
        if (user == null) {
            throw new RuntimeException("无效的Token");
        }
        // 检查用户是否有足够权限
        if (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin")) {
            throw new RuntimeException("权限不足");
        }

        Announcement ann = new Announcement();
        ann.setMessage(message);
        ann.setPublisher(user.getUsername());
        ann.setAvatar(user.getAvatar());
        return announcementMapper.insert(ann);
    }
}


