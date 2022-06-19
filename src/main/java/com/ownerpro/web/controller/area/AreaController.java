package com.ownerpro.web.controller.area;

import com.ownerpro.web.common.Result;
import com.ownerpro.web.service.area.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("领域Controller")
@Validated
@RequestMapping("/area")
public class AreaController {
    @Autowired
    AreaService areaService;

    //get all areas
    @RequiresRoles("user")
    @GetMapping("/all")
    public Object getAllAreas() {
        return areaService.getAllAreas();
    }

    @RequiresRoles("admin")
    @PostMapping("/add")
    @ApiOperation("添加领域 如果没有父节点 那么parent_area为空")
    public Result addArea(@RequestParam String area, @RequestParam String parent_area){
        return areaService.addArea(area, parent_area);
    }
}
