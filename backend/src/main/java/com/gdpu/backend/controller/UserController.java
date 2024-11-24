package com.gdpu.backend.controller;

import com.gdpu.backend.dto.Result;
import com.gdpu.backend.entity.User;
import com.gdpu.backend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@CrossOrigin
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping(value = "user/login")
    public Result login(@RequestBody User user) {
        User login = userService.login(user);
        if (Objects.nonNull(login)) {
            return new Result(login);
        } else {
            return new Result(-1, "用户名或密码错误");
        }
    }

    @GetMapping(value = "/user/info")
    public Result getUserByToken(String token) {
        User user = userService.getUserByToken(token);
        if (Objects.nonNull(user)) {
            return new Result(user);
        }else {
            return new Result(-1, "您已在其他地方登录");
        }
    }

    @PostMapping(value = "/user/logout")
    public Result logout(@RequestHeader("X-Token") String token) {
        boolean success = userService.logout(token);
        if (success) {
            return new Result();
        } else {
            return new Result(-1, "登出失败");
        }
    }
}
