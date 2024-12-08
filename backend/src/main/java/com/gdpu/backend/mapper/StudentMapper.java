package com.gdpu.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdpu.backend.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
	
}
