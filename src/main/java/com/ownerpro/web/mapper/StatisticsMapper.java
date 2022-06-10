package com.ownerpro.web.mapper;


import com.ownerpro.web.MyMapper;
import com.ownerpro.web.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsMapper extends MyMapper<Article> {
    //divide into 4 count of search result by the label of writer keyword type and area
    @Select("SELECT DISTINCT count(*) FROM article NATURAL JOIN article_writer NATURAL JOIN writer WHERE name LIKE CONCAT('%',#{content},'%')")
    int countByWriter(@Param("content")String content);

    @Select("SELECT DISTINCT count(*) FROM article NATURAL JOIN article_keyword NATURAL JOIN keyword WHERE name LIKE CONCAT('%',#{content},'%')")
    int countByKeyword(@Param("content")String content);

    @Select("SELECT DISTINCT count(*) FROM article NATURAL JOIN article_type NATURAL JOIN type WHERE name LIKE CONCAT('%',#{content},'%')")
    int countByType(@Param("content")String content);

    @Select("SELECT DISTINCT count(*) FROM article NATURAL JOIN article_area NATURAL JOIN area WHERE name LIKE CONCAT('%',#{content},'%')")
    int countByArea(@Param("content")String content);
}
