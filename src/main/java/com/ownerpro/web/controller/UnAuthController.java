package com.ownerpro.web.controller;

import com.ownerpro.web.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnAuthController {

    @RequestMapping(value = "/unauth")
    public Result unauth() {
        return Result.fail("无权限访问");
    }

}
