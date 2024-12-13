package com.gdpu.backend.mapper;

import com.gdpu.backend.entity.RepairVO;
import com.gdpu.backend.entity.Repair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RepairMapper extends BaseMapper<Repair> {

    @Select("select t1.*,t2.username as student from repair t1 left join user t2 on t2.userId=t1.applicant where t1.id=#{id}")
    public RepairVO getRepairInfo(Integer id);
    /**最终查询得到的结果会按照 RepairVO 的结构进行映射并返回，通过这个方法可以获取到指定 id 的维修记录及其相关的申请人用户名信息
     *
     */
}
//Repair映射接口，对Repair实体类进行与数据库相关的操作