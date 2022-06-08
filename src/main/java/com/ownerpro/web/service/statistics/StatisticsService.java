package com.ownerpro.web.service.statistics;

import com.ownerpro.web.common.Result;
import org.springframework.stereotype.Repository;


public interface StatisticsService {
    //count the number of label search results
    Result countByLabel(String content, String label);
}

