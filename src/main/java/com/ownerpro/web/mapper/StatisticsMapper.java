package com.ownerpro.web.mapper;


import com.ownerpro.web.MyMapper;
import com.ownerpro.web.controller.statistics.StatisticResponse;
import com.ownerpro.web.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsMapper extends MyMapper<Article> {
    //divide into 4 count of search result by the label of writer keyword type and area
    @Select("SELECT DISTINCT count(*) FROM article NATURAL JOIN article_writer NATURAL JOIN writer GROUP BY name")
    List<StatisticResponse> countByWriter();

    @Select("SELECT DISTINCT count(*) FROM article NATURAL JOIN article_keyword NATURAL JOIN keyword  GROUP BY name")
    List<StatisticResponse> countByKeyword();

    @Select("SELECT DISTINCT count(*) FROM article NATURAL JOIN article_type NATURAL JOIN type  GROUP BY name")
    List<StatisticResponse> countByType();

    @Select("SELECT DISTINCT count(*) FROM article NATURAL JOIN article_area NATURAL JOIN area  GROUP BY name")
    List<StatisticResponse> countByArea();
}
