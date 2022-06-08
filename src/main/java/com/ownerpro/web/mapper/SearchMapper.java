package com.ownerpro.web.mapper;

import com.ownerpro.web.MyMapper;
import com.ownerpro.web.entity.Article;
import com.ownerpro.web.entity.SearchRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchMapper extends MyMapper<SearchRecord>  {
    @Select("SELECT DISTINCT content FROM search_record WHERE id = #{id}")
    List<String> getRecord(@Param("id")Integer id);
    //search by writer
    @Select("SELECT DISTINCT article_id,title, magazine, date, abstract_content, url, upload_time, comment_num FROM article NATURAL JOIN article_writer NATURAL JOIN writer WHERE name LIKE CONCAT('%',#{content},'%') and title LIKE CONCAT('%',#{text},'%')")
    List<Article> searchByWriter(@Param("text")String text, @Param("content")String content);
    //search by type
    @Select("SELECT DISTINCT article_id, title,magazine, date, abstract_content, url, upload_time, comment_num FROM article NATURAL JOIN article_type NATURAL JOIN type WHERE name LIKE CONCAT('%',#{content},'%')")
    List<Article> searchByType(@Param("text")String text, @Param("content")String content);
    //search by keyword
    @Select("SELECT DISTINCT article_id,title, magazine, date, abstract_content, url, upload_time, comment_num FROM article NATURAL JOIN article_keyword NATURAL JOIN keyword WHERE name LIKE CONCAT('%',#{content},'%')")
    List<Article> searchByKeyword(@Param("text")String text, @Param("content")String content);
    //search by area
    @Select("SELECT DISTINCT article_id,title, magazine, date, abstract_content, url, upload_time, comment_num FROM article NATURAL JOIN article_area NATURAL JOIN area WHERE name LIKE CONCAT('%',#{content},'%')")
    List<Article> searchByArea(@Param("text")String text, @Param("content")String content);
}
