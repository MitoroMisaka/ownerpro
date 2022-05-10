package com.ownerpro.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.dreamlu.mica.xss.core.XssCleanIgnore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
*    author = "rai
*    date = "2022-5-10"
*    desc = "Xss过滤测试器"
*/

@RestController
@Api("Xss攻击Controller")
@Validated
@RequestMapping("/xss")
public class XssController {
    @GetMapping("/test")
    @ApiOperation("测试Xss攻击")
    @ApiImplicitParam(name = "test", value = "Xss测试字段", required = true, dataType = "String")
    public String test(String test) {
        System.out.println(test);
        return test;
    }
}
