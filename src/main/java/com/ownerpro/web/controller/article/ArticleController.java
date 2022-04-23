package com.ownerpro.web.controller.article;


import com.ownerpro.web.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("登录Controller")
@Validated
@RequestMapping("/article")
public class ArticleController {
    //an interface to add an article
    @PostMapping("/add")
    @ApiOperation(value = "添加文章", notes = "添加文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "writer", value = "作者", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "magazine", value = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "date", value = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "url", value = "", required = true, paramType = "query", dataType = "String")
    })
    public Result addArticle() {
        return Result.success("添加成功");
    }
}

