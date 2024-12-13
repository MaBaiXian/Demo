package com.gdpu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdpu.backend.entity.Repair;
import com.gdpu.backend.entity.RepairDto;
import com.gdpu.backend.mapper.RepairMapper;
import com.gdpu.backend.service.RepairService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gdpu.backend.entity.RepairVO;

import jakarta.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class RepairServiceImpl implements RepairService {
    @Resource
    RepairMapper repairMapper;

    @Override
    public int saveRepair(Repair repair) {
        repair.setStatus(0);
        int rt= repairMapper.insert(repair);
        return rt;
    }

    @Override
    public List<Repair> getAll(Repair repair) {
        List<Repair> list =repairMapper.selectList(null);
        return list;
    }

    @Override
    public Page<Repair> selectRepair(Page page, RepairDto repairDto) {
        QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();
        if(!"".equals(repairDto.getTitle())&&repairDto.getTitle()!=null){
            queryWrapper.
                    like("title",repairDto.getTitle());
        }

        if(repairDto.getStatus()!=null){
            queryWrapper.eq("status",repairDto.getStatus());
        }

        if(!"".equals(repairDto.getBeginTime())&&repairDto.getBeginTime()!=null&&!"".equals(repairDto.getEndTime())&&repairDto.getEndTime()!=null){
            queryWrapper.between("application_time",repairDto.getBeginTime(),repairDto.getEndTime());
        }


        return repairMapper.selectPage(page,queryWrapper);
    }

    @Override
    public int updateRepair(Repair repair) {
        return repairMapper.updateById(repair);
    }


    @Override
    public int deleteRepair(Integer id) {
        return repairMapper.deleteById(id);
    }


    /**
     * 查询单个报修信息
     *
     * @param id
     * @return
     */
    @Override
    public RepairVO getRepairInfo(Integer id){
        RepairVO repairVO =    repairMapper.getRepairInfo(id);

        return repairVO;
    }

}
