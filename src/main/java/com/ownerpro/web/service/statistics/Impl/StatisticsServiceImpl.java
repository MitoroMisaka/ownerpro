package com.ownerpro.web.service.statistics.Impl;

import com.ownerpro.web.common.Result;
import com.ownerpro.web.mapper.StatisticsMapper;
import com.ownerpro.web.service.statistics.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsMapper statisticsMapper;

    @Override
    public Result countByLabel( String label) {
        //divide into 4 count of search result by the label of writer keyword type and area
        if(label.equals("writer")){
            return Result.success("success", statisticsMapper.countByWriter());
        }
        else if(label.equals("keyword")){
            return Result.success("success", statisticsMapper.countByKeyword());
        }
        else if(label.equals("type")){
            return Result.success("success", statisticsMapper.countByType());
        }
        else if(label.equals("area")){
            return Result.success("success", statisticsMapper.countByArea());
        }
        else{
            return Result.fail("error", "label is not correct");
        }
    }

}

