package com.gdpu.backend.service;
import com.gdpu.backend.entity.RepairDto;
import com.gdpu.backend.entity.RepairVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdpu.backend.entity.Repair;

import java.util.List;

public interface RepairService {
    /**
     * 保存维修申请记录
     * @param repair
     * @return
     */
    int saveRepair(Repair repair);

    /**
     * 获取维修申请列表
     * @param repair
     * @return
     */
    List<Repair> getAll(Repair repair);

    /**
     * 分页查询
     * @param page
     * @param repairDto
     * @return
     */

    IPage<Repair> selectRepair(Page page, RepairDto repairDto);

    /**
     * 修改维修申请
     * @param repair
     * @return
     */
    int updateRepair(Repair repair);

    /**
     * 删除维修申请
     * @param id
     * @return
     */

    int deleteRepair(Integer id);
    /**
     * 查询单个报修信息
     *
     * @param id
     * @return
     */
    RepairVO getRepairInfo(Integer id);

}
