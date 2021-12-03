package com.phoenix.logistics.controller.admin;

import com.phoenix.logistics.common.Result;
import com.phoenix.logistics.service.admin.AdminService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@RestController
@Api("管理员控制器")
@RequestMapping("/car")
@Validated
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequiresRoles("admin")
    @GetMapping("/cars")
    @ApiOperation("查看所有车辆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize",value = "每页显示数量 (不小于0)",required = true,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "页数 (不小于0)", required = true, paramType = "query", dataType = "Integer")})
    public Result getAllCars(@NotNull @RequestParam("pageSize")Integer pageSize,
                         @NotNull @RequestParam("pageNum")Integer pageNum){
        return Result.success(adminService.getAllCars(pageNum,pageSize));
    }

    @RequiresRoles("admin")
    @PostMapping("/add_car")
    @ApiOperation("添加车辆")
    @ApiImplicitParam(name = "num",value = "添加的车辆数量（不写默认为1）",paramType = "query",dataType = "Integer")
    public Result AddCar(@RequestParam(value = "num",defaultValue = "1")Integer num){
        adminService.addCar(num);
        return Result.success("添加成功");
    }
}
