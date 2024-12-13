package com.gdpu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdpu.backend.entity.Fee;
import com.gdpu.backend.mapper.FeeMapper;
import com.gdpu.backend.service.FeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeeServiceImpl implements FeeService {
    @Autowired
    private FeeMapper feeMapper;

    @Override
    public List<Fee> getAllFees() {
        QueryWrapper<Fee> queryWrapper = new QueryWrapper<>();
        return feeMapper.selectList(queryWrapper); // 获取所有费用记录
    }

    @Override
    public Fee getFeeByStuId(String stuId) {
        QueryWrapper<Fee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stuId", stuId); // 假设学号字段名为stu_id
        return feeMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean addFee(Fee fee) {
        return feeMapper.insert(fee) > 0;
    }

    @Override
    public boolean updateFee(Fee fee) {
        return feeMapper.updateById(fee) > 0;
    }

    @Override
    public boolean deleteFeeByStuId(String stuId) {
        QueryWrapper<Fee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stuId", stuId);
        return feeMapper.delete(queryWrapper) > 0;
    }
}
