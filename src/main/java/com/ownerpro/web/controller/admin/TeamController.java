package com.ownerpro.web.controller.admin;

import com.ownerpro.web.common.Result;
import com.ownerpro.web.entity.Team;
import com.ownerpro.web.exception.RRException;
import com.ownerpro.web.service.team.TeamService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Api("管理员团队控制器")
@RequestMapping("/team")
@Validated
public class TeamController {

    @Autowired
    private TeamService teamService;

    /*@RequiresRoles("admin")
    @GetMapping("/all")
    @ApiOperation("查看所有车辆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize",value = "每页显示数量 (不小于0)",required = true,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "页数 (不小于0)", required = true, paramType = "query", dataType = "Integer")})
    public Result getAllCars(@NotNull @RequestParam("pageSize")Integer pageSize,
                             @NotNull @RequestParam("pageNum")Integer pageNum){
        return Result.success(teamService.getTeam());
    }*/

    @RequiresRoles("user")
    @PostMapping("/add")
    @ApiOperation("添加团队")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "添加的团队名",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "age",value = "添加的团队年龄",paramType = "query",dataType = "Short"),
            @ApiImplicitParam(name = "hobby",value = "添加的团队爱好",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "contact",value = "添加的团队方式",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "introduction",value = "添加的团队介绍",paramType = "query",dataType = "String")
            })
    public Result AddCar(@RequestParam(value = "name",defaultValue = "1")String name,
                         @RequestParam(value = "age",defaultValue = "0")Short age ,
                         @RequestParam(value = "hobby")String hobby,
            @RequestParam(value = "contact")String contact,
            @RequestParam(value = "introduction")String introduction){
        teamService.signUp(name, age, hobby, contact, introduction);
        return Result.success("添加成功");
    }

    @RequiresRoles("user")
    @PostMapping("/update_age")
    @ApiOperation("修改年龄")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "修改的团队名称", paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "age",value = "修改的团队年龄", paramType = "query",dataType = "Short")
    })
    public Result ChangeAge(@RequestParam(value = "name")String name,
                            @RequestParam(value = "age")Short age){
        try{
            teamService.changeAge(age, name);
        }catch(RRException e){
            return Result.fail("修改失败");
        }
        return Result.success("修改成功");
    }

    @RequiresRoles("user")
    @PostMapping("/update_hobby")
    @ApiOperation("修改爱好")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "修改的团队名称", paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "hobby",value = "修改的团队爱好", paramType = "query",dataType = "String")
    })
    public Result ChangeHobby(@RequestParam(value = "name")String name,
                            @RequestParam(value = "String")String hobby){
        try{
            teamService.changeHobby(hobby, name);
        }catch(RRException e){
            return Result.fail("修改失败");
        }
        return Result.success("修改成功");
    }

    @RequiresRoles("user")
    @PostMapping("/update_introduction")
    @ApiOperation("修改团队介绍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "修改的团队名称", paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "introduction",value = "修改的团队介绍", paramType = "query",dataType = "String")
    })
    public Result ChangeIntro(@RequestParam(value = "name")String name,
                              @RequestParam(value = "introduction")String introduction){
        try{
            teamService.changeIntroduction(introduction, name);
        }catch(RRException e){
            return Result.fail("修改失败");
        }
        return Result.success("修改成功");
    }


    @GetMapping("/info")
    @ApiOperation("获得团队详情")
    @ApiImplicitParam(name = "name",value = "要获取的团队名称", paramType = "query",dataType = "String")
    public Team ChangeContact(@RequestParam(value = "name")String name){
        return teamService.getTeam(name);
    }



}
