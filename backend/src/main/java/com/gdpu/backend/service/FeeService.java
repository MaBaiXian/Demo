package com.gdpu.backend.service;

import com.gdpu.backend.entity.Fee;

import java.util.List;

public interface FeeService {
    List<Fee> getAllFees();
    Fee getFeeByStuId(String stuId);
    boolean addFee(Fee fee);
    boolean updateFee(Fee fee);
    boolean deleteFeeByStuId(String stuId);

}
