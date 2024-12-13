package com.gdpu.backend.entity;

import com.gdpu.backend.entity.Repair;
import lombok.Data;

// repair value objection
/**继承Repair实体类并新增特定属性，构建了一个用于展示和传递维修相关详细信息的对象结构
 *
 */
@Data
public class RepairVO extends Repair {
    private String student;
}
