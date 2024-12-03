package com.gdpu.backend.controller;

import com.gdpu.backend.dto.Result;
import com.gdpu.backend.entity.Admin;
import com.gdpu.backend.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AdminManageController {
    @Resource
    private AdminService adminService;

    @GetMapping(value = "/Admin/getAdminList")
    public Result getAdminList(@RequestHeader("X-Token") String token) {
        try {
            List<Admin> admins = adminService.getAdminByToken(token);
            if (admins != null && !admins.isEmpty()) {
                return new Result(admins);
            } else {
                return new Result();
            }
        } catch (RuntimeException e) {
            return new Result(50008, "token验证失败");
        }
    }

    @DeleteMapping(value = "/Admin/deleteAdmin/{id}")
    public Result deleteAdmin(@RequestHeader("X-Token") String token, @PathVariable int id) {
        try {
            int result = adminService.deleteAdmin(token, id);
            if (result > 0) {
                return new Result(result);
            } else {
                return new Result(-1, "未找到对应的管理员");
            }
        } catch (RuntimeException e) {
            return new Result(-1, e.getMessage());
        }
    }

    @PutMapping("/Admin/updateAdmin/{id}")
    public Result updateAdmin(@RequestHeader("X-Token") String token, @PathVariable int id, @RequestBody Admin admin) {
        try {
            int result = adminService.updateAdmin(token, id, admin);
            if (result > 0) {
                return new Result(result);
            } else {
                return new Result(-1, "未找到对应的管理员");
            }
        } catch (RuntimeException e) {
            return new Result(-1, e.getMessage());
        }
    }

    @PostMapping("/Admin/createAdmin")
    public Result createAdmin(@RequestHeader("X-Token") String token, @RequestBody Admin admin) {
        try {
            int result = adminService.createAdmin(token, admin);
            if (result > 0) {
                return new Result(result);
            } else {
                return new Result(-1, "创建管理员失败");
            }
        } catch (RuntimeException e) {
            return new Result(-1, e.getMessage());
        }
    }
}
