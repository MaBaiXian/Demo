package com.gdpu.backend.service.impl;

import com.gdpu.backend.entity.Announcement;
import com.gdpu.backend.entity.User;
import com.gdpu.backend.mapper.AnnouncementMapper;
import com.gdpu.backend.service.AnnouncementService;
import com.gdpu.backend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        User user =  userService.getUserByToken(token);

        if (user == null) {
            throw new RuntimeException("无效的Token");
        } else {
            QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
            queryWrapper.last("ORDER BY time DESC LIMIT 3");
            return announcementMapper.selectList(queryWrapper);
        }
    }
}
