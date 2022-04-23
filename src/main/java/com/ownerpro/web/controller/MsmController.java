package com.ownerpro.web.controller;


import com.ownerpro.web.common.Result;
import com.ownerpro.web.service.MsmService;
import com.ownerpro.web.util.MailUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;


@RestController
@Api("发送邮件接口")
@RequestMapping("/mail")
@Validated
public class MsmController {
    @Autowired
    private MsmService msmService;

    @PostMapping("/send")
    @ApiOperation("发送邮箱验证码")
    @ApiImplicitParam(name = "email", value = "需要验证码的邮箱", paramType = "query", dataType = "String")
    public Result sendEmail(@NotNull String email) {
        if(MailUtil.checkEmail(email) == true){
            int code = MailUtil.getRamdomNum();
            msmService.send(email, code);
            return Result.success(code);
        }else {
            return Result.fail("邮箱格式不正确");
        }
    }
}
