package com.gdpu.backend.service;

import com.gdpu.backend.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getStudentByToken(String token);
    int deleteStudent(String token, int id);
    int updateStudent(String token, int id, Student student);
    int createStudent(String token, Student student);
} 