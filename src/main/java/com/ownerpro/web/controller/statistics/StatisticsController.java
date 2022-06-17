package com.ownerpro.web.controller.statistics;

import com.ownerpro.web.common.Result;
import com.ownerpro.web.service.statistics.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("统计Controller")
@Validated
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    //count the number of label search results
    @GetMapping("/by_label")
    @ApiOperation("根据标签统计")
    public Result searchByLabel( @RequestParam("label")String label){
        return statisticsService.countByLabel(label);
    }
}
