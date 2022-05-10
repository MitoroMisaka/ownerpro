package com.ownerpro.web.controller.search;

import com.ownerpro.web.common.Result;
import com.ownerpro.web.dto.UserDTO;
import com.ownerpro.web.mapper.ArticleMapper;
import com.ownerpro.web.mapper.UserMapper;
import com.ownerpro.web.service.search.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("搜索Controller")
@Validated
@RequestMapping("/search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/title")
    @ApiOperation("根据标题搜索(实际上发布会议，主要内容这些都在搜)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "orderBy", value = "排序", required = true, dataType = "String")
    })
    public Object searchByTitle(@RequestParam("content")String content, @RequestParam("pageSize")Integer pageSize,
                                             @RequestParam("pageNum")Integer pageNum, @RequestParam("orderBy")String orderBy) {
        return searchService.searchTitle(content, pageSize, pageNum, orderBy);
    }

    @GetMapping("/history")
    @ApiOperation("个人搜索历史")
    //requires id
    public Result searchHistory(){
        UserDTO principle = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        String username = principle.getUsername();
        Integer id = userMapper.getIdByUsername(username);
        return Result.success("success", searchService.getSearchRecord(id));
    }

    @GetMapping("/by_label")
    @ApiOperation("根据标签搜索")
    public Result searchByLabel(@RequestParam("content")String content, @RequestParam("label")String label,
                                @RequestParam("pageSize")Integer pageSize, @RequestParam("pageNum")Integer pageNum){
        return Result.success("success", searchService.searchByLabel(content, label, pageSize, pageNum));
    }

}
