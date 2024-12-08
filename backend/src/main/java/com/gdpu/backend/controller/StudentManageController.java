package com.gdpu.backend.controller;

import com.gdpu.backend.dto.Result;
import com.gdpu.backend.entity.Student;
import com.gdpu.backend.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class StudentManageController {
    @Resource
    private StudentService studentService;

    @GetMapping(value = "/Student/getStudentList")
    public Result getStudentList(@RequestHeader("X-Token") String token) {
        try {
            List<Student> students = studentService.getStudentByToken(token);
            if (students != null && !students.isEmpty()) {
                return new Result(students);
            } else {
                return new Result();
            }
        } catch (RuntimeException e) {
            return new Result(50008, "token验证失败");
        }
    }

    @DeleteMapping(value = "/Student/deleteStudent/{id}")
    public Result deleteStudent(@RequestHeader("X-Token") String token, @PathVariable int id) {
        try {
            int result = studentService.deleteStudent(token, id);
            if (result > 0) {
                return new Result(result);
            } else {
                return new Result(-1, "未找到对应的学生");
            }
        } catch (RuntimeException e) {
            return new Result(-1, e.getMessage());
        }
    }

    @PutMapping("/Student/updateStudent/{id}")
    public Result updateStudent(@RequestHeader("X-Token") String token, @PathVariable int id, @RequestBody Student student) {
        try {
            int result = studentService.updateStudent(token, id, student);
            if (result > 0) {
                return new Result(result);
            } else {
                return new Result(-1, "未找到对应的学生");
            }
        } catch (RuntimeException e) {
            return new Result(-1, e.getMessage());
        }
    }

    @PostMapping("/Student/createStudent")
    public Result createStudent(@RequestHeader("X-Token") String token, @RequestBody Student student) {
        try {
            int result = studentService.createStudent(token, student);
            if (result > 0) {
                return new Result(result);
            } else {
                return new Result(-1, "创建学生失败");
            }
        } catch (RuntimeException e) {
            return new Result(-1, e.getMessage());
        }
    }
}
