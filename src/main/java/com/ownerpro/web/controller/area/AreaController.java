package com.ownerpro.web.controller.area;

import com.ownerpro.web.service.area.AreaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("领域Controller")
@Validated
@RequestMapping("/area")
public class AreaController {
    @Autowired
    AreaService areaService;

    //get all areas
    @GetMapping("/all")
    public Object getAllAreas() {
        return areaService.getAllAreas();
    }
}
