package com.gdpu.backend.controller;
import com.gdpu.backend.entity.RepairVO;
import com.gdpu.backend.entity.RepairDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdpu.backend.dto.Result;
import com.gdpu.backend.entity.Repair;
import com.gdpu.backend.service.RepairService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.Objects;

@CrossOrigin//解决跨域问题
@RestController//处理 Web 请求
public class RepairController {

    //将RepairService的实现类实例注入到RepairController类的repaidService成员变量中
    @Resource
    private RepairService repairService;

    //将add方法映射为一个处理 HTTP POST 请求的方法
    @PostMapping(value = "repair/add")
    public Result add(@RequestBody Repair repair) {

        if (repair.getTitle() == null || repair.getTitle().isEmpty()) {
            return new Result(-1, "维修标题不能为空");
        }
        if (repair.getContent() == null || repair.getContent().isEmpty()) {
            return new Result(-1, "维修内容不能为空");
        }

        //用于存储调用repairService的saveRepair方法执行后的返回结果
        int rt = repairService.saveRepair(repair);

        if (rt>0) {
            return new Result(20000,"提交申请成功");
        } else {
            return new Result(-1, "提交申请失败！");
        }
    }

    //更新维修信息
    //将update方法映射为一个处理 HTTP POST 请求的方法
    @PostMapping(value = "repair/update")
    public Result update(@RequestBody RepairDto repair) {//接收RepairDto类型的参数，参数值从请求体中获取
        Repair repair1=new Repair();

        //复制repair对象的属性值到repair1中
        BeanUtils.copyProperties(repair,repair1);
        int rt = repairService.updateRepair(repair1);
        if (rt>0) {
            return new Result("修改成功！");
        } else {
            return new Result(-1, "修改失败！");
        }
    }

    //获取维修信息列表的分页数据
    //将list方法映射为一个处理 HTTP POST 请求的方法，current，size是路径变量
    @PostMapping(value = "repair/list/{current}/{size}")
    public Result list(@ApiParam(name = "current",value = "当前页",required = true) @PathVariable int current,
                       @ApiParam(name = "size",value = "每页显示条数",required = true) @PathVariable int size,
                       @RequestBody(required = false) RepairDto repairDto){

        Page page =new Page(current,size);
        IPage<Repair> repairIPage= repairService.selectRepair(page,repairDto);

        //把维修申请返回给舍管端
        return new Result(repairIPage);
    }

    //获取学生维修申请信息
    @GetMapping(value = "/repair/info/{repairId}")
    public Result getRepairInfo(@PathVariable Integer repairId) {

        //调用repairService的getRepairInfo方法,传入repairId作为参数,在数据库中查询对应repairId的维修记录
        RepairVO repairVO = repairService.getRepairInfo(repairId);

        //根据repairVO是否为空来判断查询操作是否成功
        if (Objects.nonNull(repairVO)) {
            return new Result(repairVO);
        }else {
            return new Result(-1, "信息查询失败！");
        }
    }

    //删除指定维修记录
    @GetMapping(value = "/repair/delete/{id}")
    public Result deleteRepair(@PathVariable Integer id){
        int i =   repairService.deleteRepair(id);
        if(i>0){
            return new Result("删除成功！");
        }else {
            return new Result(-1, "删除失败！");
        }
    }

}
/*维修申请提交功能的后端控制器。它接收前端发送过来的维修申请数据，通过调用相关的服务方法将申请保存到数据库，
  并根据保存操作的结果返回相应的状态码和提示消息给前端。
*/