package com.ownerpro.web.controller.account;

import com.ownerpro.web.controller.request.UpdateUserMessageRequest;
import com.ownerpro.web.controller.response.GetAdminResponse;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import com.ownerpro.web.exception.RRException;
import com.ownerpro.web.controller.response.GetUserResponse;
import com.ownerpro.web.service.account.AccountService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ownerpro.web.common.Result;
import com.ownerpro.web.dto.UserDTO;
import com.ownerpro.web.util.PasswordUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;

@RestController
@Api("登录Controller")
@Validated
@RequestMapping("/account")
public class AccountController {

    @PostMapping("/signIn")
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码(长度6-20)", required = true, paramType = "query", dataType = "String")
    })
    public Result doLogin(@RequestParam("username")@NotNull String username, @RequestParam("password")@NotNull @Size(min = 6,max = 20)String password ){

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password) ) {
            return Result.fail("用户名,密码和姓名不能为空！");
        }
        AuthenticationToken token = new UsernamePasswordToken(username, PasswordUtil.convert(password));

        try {

            //尝试登陆，将会调用realm的认证方法
            SecurityUtils.getSubject().login(token);

        }catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                return Result.fail("用户不存在");
            } else if (e instanceof LockedAccountException) {
                return Result.fail("用户被禁用");
            } else if (e instanceof IncorrectCredentialsException) {
                return Result.fail("密码错误");
            } else {
                return Result.fail("用户认证失败");
            }
        }

        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        if(principal.getType()==1) return Result.success("登录成功",new GetAdminResponse(accountService.getAdmin(username),principal.getType()));
        return Result.success("登录成功", new GetUserResponse(accountService.getUser(username),principal.getType()));
    }

    @Autowired
    public AccountService accountService;

    @PostMapping("/signUp")
    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名（3到20个字符）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码（6到20位）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "姓名（3到20个字符）", required = true, paramType = "query", dataType = "String")})
    public Result signUp(@NotNull @Size(max = 20,min = 3) @RequestParam("username")String username,
                         @NotNull @Size(max = 20,min = 6) @RequestParam("password")String password,
                         @NotNull @Size(max = 20,min = 3) @RequestParam("name")String name) throws IOException {
        accountService.signUp(username,password,name);
        return Result.success("注册成功!");
    }


    @PostMapping("/signOut")
    @ApiOperation("登出")
    public Result logout() throws IOException {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

    @PostMapping("/changePassword")
    @ApiOperation("修改密码")
    @ApiImplicitParam(name = "password", value = "密码（6到20位）", required = true, paramType = "query", dataType = "String")
    public Result changePassword(@NotNull @Size(max = 20,min = 6) @RequestParam("password")String password){
        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        String username = principal.getUsername();
        try {
            accountService.changePassword(username, password);
        }catch (RRException e){
            if(e.getEnumExceptionType().getErrorCode()==1001)
                return Result.fail("新密码不能和旧密码一样！");
        }
        return Result.success("修改成功！");
    }

    @GetMapping("/checkPassword")
    @ApiOperation("判断密码是否相同")
    @ApiImplicitParam(name = "password", value = "密码（6到20位）", required = true, paramType = "query", dataType = "String")
    public Result checkPassword(@NotNull @Size(max = 20,min = 6) @RequestParam("password")String password){
        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        String username = principal.getUsername();
        try {
            accountService.checkPassword(username, password);
        }catch (RRException e){
            if(e.getEnumExceptionType().getErrorCode()==1001)
                return Result.fail("新密码不能和旧密码一样！");
        }
        return Result.success("新密码符合规范");
    }


    @GetMapping("/checkUsername")
    @ApiOperation("检验用户名是否重复")
    public Result checkUsername(@NotNull @RequestParam("username")String username){
        try{
            accountService.checkUsername(username);
        }catch (RRException e){
            if(e.getEnumExceptionType().getErrorCode()==1002)
                return Result.fail("用户名重复了，请换一个用户名");
        }
        return Result.success("可以注册");
    }

    @RequiresRoles("admin")
    @GetMapping("/all")
    @ApiOperation("获取一个登录者的所有信息")
    public Result getMessage(){
        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        String username = principal.getUsername();
        if(principal.getType()==0){
            User user = accountService.getUser(username);
            return Result.success("获取成功",new GetUserResponse(user,principal.getType()));
        }else{
            Admin admin = accountService.getAdmin(username);
            return Result.success("获取成功",new GetAdminResponse(admin,principal.getType()));
        }
    }


}
