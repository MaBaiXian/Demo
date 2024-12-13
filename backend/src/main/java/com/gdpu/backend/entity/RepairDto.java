package com.gdpu.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
/**数据传输对象
 *
 */
public class RepairDto extends BaseEntity{

    private Integer id;

    private String title;

    private Integer applicant;

    private Date applicationTime;

    private Integer importance;

    private String content;

    private String imageUri;

    private Integer status;

}
