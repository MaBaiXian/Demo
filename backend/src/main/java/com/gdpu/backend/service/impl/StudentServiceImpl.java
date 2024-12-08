package com.gdpu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdpu.backend.entity.Student;
import com.gdpu.backend.entity.User;
import com.gdpu.backend.mapper.StudentMapper;
import com.gdpu.backend.service.StudentService;
import com.gdpu.backend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentMapper studentMapper;
    @Resource
    private UserService userService;

    @Override
    public int updateStudent(String token, int id, Student student) {
        // 根据token获取用户信息
        User user = userService.getUserByToken(token);

        if (user == null || (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin"))) {
            throw new RuntimeException("无效的Token或权限不足");
        }

        // 检查学生是否存在
        Student existingStudent = studentMapper.selectById(id);
        if (existingStudent == null) {
            return 0; // 如果不存在，返回0
        }
        // 执行更新操作
        return studentMapper.updateById(student);
    }

    @Override
    public List<Student> getStudentByToken(String token) {
        // 根据token获取用户信息
        User user = userService.getUserByToken(token);

        if (user == null || (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin"))) {
            throw new RuntimeException("无效的Token或权限不足");
        }

        // 使用QueryWrapper构建查询条件
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roles", "student"); // 筛选roles为student的用户

        // 执行查询
        return studentMapper.selectList(queryWrapper);
    }

    @Override
    public int deleteStudent(String token, int id) {
        // 根据token获取用户信息
        User user = userService.getUserByToken(token);

        if (user == null || (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin"))) {
            throw new RuntimeException("无效的Token或权限不足");
        }

        // 检查学生是否存在
        Student student = studentMapper.selectById(id);
        if (student == null) {
            return 0; // 如果不存在，返回0
        }
        // 执行删除操作
        return studentMapper.deleteById(id);
    }

    @Override
    public int createStudent(String token, Student student) {
        // 根据token获取用户信息
        User user = userService.getUserByToken(token);

        if (user == null || (!user.getRoles().equals("SysAdmin") && !user.getRoles().equals("DormAdmin"))) {
            throw new RuntimeException("无效的Token或权限不足");
        }

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", student.getUsername());
        Long existingStudentCount = studentMapper.selectCount(queryWrapper);
        if (existingStudentCount > 0) {
            // 如果已存在相同用户名的学生，抛出异常
            throw new RuntimeException("学生用户名已存在");
        }
        
        student.setAvatar("https://www.emojiall.com/images/240/microsoft/windows-11-23H2/1f60d.png");
        student.setRoles("student");
    
        // 执行创建操作
        return studentMapper.insert(student);
    }
} 