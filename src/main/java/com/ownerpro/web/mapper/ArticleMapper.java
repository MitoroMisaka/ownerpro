package com.ownerpro.web.mapper;

import com.ownerpro.web.MyMapper;
import com.ownerpro.web.controller.article.ArticleListResponse;
import com.ownerpro.web.entity.Article;
import com.ownerpro.web.entity.Comment;
import com.ownerpro.web.entity.Note;
import com.ownerpro.web.entity.Reference;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * article  generated at 2022/4/29 by:rai
 */
@Repository
public interface ArticleMapper extends MyMapper<Article> {
    @Insert("INSERT INTO article (title,magazine,date,url,abstract_content,upload_time) VALUES (#{title},#{magazine},#{date},#{url},#{abstract_content},#{upload_time})")
    void insertArticle(@Param("title") String title, @Param("magazine") String magazine, @Param("date") Timestamp date,
                       @Param("url") String url, @Param("abstract_content") String abstract_content, @Param("upload_time") Timestamp upload_time);

    @Select("SELECT article_id FROM article WHERE title = #{title}")
    Long selectIDByTitle(@Param("title") String title);

    @Insert("INSERT INTO article_writer (writer_id, article_id) VALUES (#{writer_id},#{article_id})")
    void insertArticleWriter(@Param("writer_id") Long writer_id, @Param("article_id") Long article_id);

    @Insert("INSERT INTO article_type (type_id, article_id) VALUES (#{type_id},#{article_id})")
    void insertArticleType(@Param("type_id") Long type_id, @Param("article_id") Long article_id);

    @Insert("INSERT INTO article_keyword (keyword_id, article_id) VALUES (#{keyword_id},#{article_id})")
    void insertArticleKeyword(@Param("article_id") Long article_id, @Param("keyword_id") Long keyword_id);

    @Insert("INSERT INTO article_area (area_id, article_id) VALUES (#{area_id}, #{article_id})")
    void insertArticleArea(@Param("area_id") Long area_id, @Param("article_id") Long article_id);

    //check if the writer exist in the database
    @Select("SELECT writer_id FROM writer WHERE name = #{name}")
    Long isWriterExists(@Param("name")String name);

    @Insert("INSERT INTO writer (name) VALUES (#{name})")
    void insertWriter(@Param("name") String name);

    @Select("SELECT article_writer_id FROM article_writer WHERE article_id = #{article_id} AND writer_id = #{writer_id}")
    Long isArticleWriterExists(@Param("article_id")Long article_id, @Param("writer_id")Long writer_id);

    @Select("SELECT article_type_id FROM article_type WHERE article_id = #{article_id} AND type_id = #{type_id}")
    Long isArticleTypeExists(@Param("article_id")Long article_id, @Param("type_id")Long type_id);

    @Select("SELECT area_id FROM area WHERE name = #{name}")
    Long isAreaExists(@Param("name")String name);

    @Insert("INSERT INTO area (name) VALUES (#{name})")
    void insertArea(@Param("name")String name);

    @Select("SELECT article_area_id FROM article_area WHERE article_id = #{article_id} AND area_id = #{area_id}")
    Long isArticleAreaExists(@Param("article_id")Long article_id, @Param("area_id")Long area_id);

    @Select("SELECT keyword_id FROM keyword WHERE name = #{name}")
    Long isKeywordExists(@Param("name")String name);

    @Insert("INSERT INTO keyword (name) VALUES (#{name})")
    void insertKeyword(@Param("name")String name);

    @Select("SELECT article_keyword_id FROM article_keyword WHERE article_id = #{article_id} AND keyword_id = #{keyword_id}")
    Long isArticleKeywordExists(@Param("article_id")Long article_id, @Param("keyword_id")Long keyword_id);

    @Select("SELECT type_id FROM type WHERE name = #{name}")
    Long isTypeExists(@Param("name")String name);

    @Insert("INSERT INTO type (name) VALUES (#{name})")
    void insertType(@Param("name")String name);

    @Select("SELECT article_id, title, magazine, date, abstract_content, url, upload_time, comment_num FROM article ")
    List<ArticleListResponse> getArticleList();

    @Select("SELECT article_id, title, magazine, date, abstract_content, url, upload_time FROM article WHERE article_id = #{article_id}")
    ArticleListResponse getArticleById(@Param("article_id") Long article_id);

    @Select("SELECT name FROM writer NATURAL JOIN article_writer WHERE article_id = #{article_id}")
    List<String> getWriterByArticleId(@Param("article_id")Long article_id);

    @Select("SELECT name FROM type NATURAL JOIN article_type WHERE article_id = #{article_id}")
    List<String> getTypeByArticleId(@Param("article_id") Long article_id);

    @Select("SELECT name FROM keyword NATURAL JOIN article_keyword WHERE article_id = #{article_id}")
    List<String> getKeywordByArticleId(@Param("article_id")Long article_id);

    @Select("SELECT name FROM area NATURAL JOIN article_area WHERE article_id = #{article_id}")
    List<String> getAreaByArticleId(@Param("article_id")Long article_id);

    @Select("SELECT name FROM reference NATURAL JOIN article_reference WHERE article_id = #{article_id}")
    List<String> getReferenceByArticleId(@Param("article_id")Long article_id);

    @Select("SELECT reference_id, name, url FROM reference")
    List<Reference> getAllReferences();

    @Insert("INSERT INTO article_reference (article_id, reference_id, note) VALUES (#{article_id}, #{reference_id}, #{note})")
    void insertReference(@Param("article_id")Long article_id, @Param("reference_id")Long reference_id, @Param("note")String note);


    //add note
    @Insert("INSERT INTO note (article_id, content, publisher, publish_time) VALUES (#{article_id}, #{content}, #{publisher}, #{publish_time})")
    void addNote(@Param("article_id")Long article_id, @Param("content")String content, @Param("publisher")String publisher, @Param("publish_time")Timestamp publish_time);

    @Select("SELECT name FROM user WHERE username = #{username}")
    String getNameByUsername(@Param("username")String username);

    @Select("SELECT id FROM user WHERE username = #{username}")
    Long getIdByUsername(@Param("username")String username);


    //about the comment
    @Insert("INSERT INTO comment (comment_time, content, id, likes, note_id, super_id, name) VALUES (#{comment_time}, #{content}, #{id}, 0, #{note_id}, #{super_id}, #{name})")
    void addComment(@Param("comment_time")Timestamp comment_time, @Param("content")String content, @Param("id")Long id,
                    @Param("note_id")Long note_id, @Param("super_id")Long super_id, @Param("name")String name);

    @Select("SELECT * FROM comment WHERE super_id = 0 and note_id = #{id}")
    List<Comment> getMainComment(@Param("id")Long id);

    @Select("SELECT * FROM comment WHERE super_id = #{super_id}")
    List<Comment> getSubComment(@Param("super_id")Long super_id);

    @Delete("DELETE FROM comment WHERE id = #{id}")
    void deleteCommentById(@Param("id")Long id);

    //delete type , keyword , note , reference and writer , area
    @Delete("DELETE FROM article_type WHERE article_type_id = #{type_id}")
    void deleteTypeById(@Param("type_id")Long type_id);

    @Delete("DELETE FROM article_keyword WHERE article_keyword_id = #{keyword_id}")
    void deleteKeywordById(@Param("keyword_id")Long keyword_id);

    @Delete("DELETE FROM note WHERE note_id = #{note_id}")
    void deleteNoteById(@Param("note_id")Long note_id);

    @Delete("DELETE FROM article_reference WHERE reference_id = #{reference_id}")
    void deleteReferenceById(@Param("reference_id")Long reference_id);

    @Delete("DELETE FROM article_writer WHERE article_writer_id = #{writer_id}")
    void deleteWriterById(@Param("writer_id")Long writer_id);

    @Delete("DELETE FROM article_area WHERE article_area_id = #{area_id}")
    void deleteAreaById(@Param("area_id")Long area_id);



    //delete from article , article_keyword , article_type, article_area, article_writer where id = #{id}
    @Delete("DELETE FROM article WHERE article_id = #{id}")
    void deleteArticle(@Param("id")Long id);

    @Delete("DELETE FROM article_keyword WHERE article_id = #{id}")
    void deleteArticleKeyword(@Param("id")Long id);

    @Delete("DELETE FROM article_type WHERE article_id = #{id}")
    void deleteArticleType(@Param("id")Long id);

    @Delete("DELETE FROM article_area WHERE article_id = #{id}")
    void deleteArticleArea(@Param("id")Long id);

    @Delete("DELETE FROM article_writer WHERE article_id = #{id}")
    void deleteArticleWriter(@Param("id")Long id);

    @Delete("DELETE FROM comment WHERE note_id = #{id}")
    void deleteComment(@Param("id")Long id);

    @Delete("DELETE FROM note WHERE article_id = #{id}")
    void deleteNote(@Param("id")Long id);



    @Delete("DELETE FROM article_reference WHERE article_id = #{id}")
    void deleteArticleReference(@Param("id")Long id);

    //get notes
    @Select("SELECT * FROM note WHERE article_id = #{id}")
    List<Note> getNotes(@Param("id")Long id);

    // get note by id
    @Select("SELECT * FROM note WHERE note_id = #{id}")
    Note getNoteById(@Param("id")Long id);

    //like a note and comment
    @Update("UPDATE note SET likes = likes + 1 WHERE note_id = #{id}")
    void likeNote(@Param("id")Long id);

    @Update("UPDATE comment SET likes = likes + 1 WHERE comment_id = #{id}")
    void likeComment(@Param("id")Long id);

    //update article with title, magazine, date, url, abstract_content, upload_time
    @Update("UPDATE article SET title = #{title}, magazine = #{magazine}, date = #{date}, url = #{url}, abstract_content = #{abstract_content}, upload_time = #{upload_time} WHERE article_id = #{article_id}")
    void updateArticle(@Param("article_id")Long article_id, @Param("title")String title, @Param("magazine")String magazine, @Param("date")Timestamp date, @Param("url")String url, @Param("abstract_content")String abstract_content, @Param("upload_time")Timestamp upload_time);

    //update article_keyword
    @Update("UPDATE article_keyword SET article_id = #{article_id}, keyword_id = #{keyword_id} WHERE article_keyword_id = #{article_keyword_id}")
    void updateArticleKeyword(@Param("article_id")Long article_id, @Param("keyword_id")Long keyword_id, @Param("article_keyword_id")Long article_keyword_id);

    //update article_type
    @Update("UPDATE article_type SET article_id = #{article_id}, type_id = #{type_id} WHERE article_type_id = #{article_type_id}")
    void updateArticleType(@Param("article_id")Long article_id, @Param("type_id")Long type_id, @Param("article_type_id")Long article_type_id);

    //update article_area
    @Update("UPDATE article_area SET article_id = #{article_id}, area_id = #{area_id} WHERE article_area_id = #{article_area_id}")
    void updateArticleArea(@Param("article_id")Long article_id, @Param("area_id")Long area_id, @Param("article_area_id")Long article_area_id);

    //update article_writer
    @Update("UPDATE article_writer SET article_id = #{article_id}, writer_id = #{writer_id} WHERE article_writer_id = #{article_writer_id}")
    void updateArticleWriter(@Param("article_id")Long article_id, @Param("writer_id")Long writer_id, @Param("article_writer_id")Long article_writer_id);

    //get article_area_id by article_id
    @Select("SELECT article_area_id FROM article_area WHERE article_id = #{article_id}")
    List<Long> getArticleAreaId(@Param("article_id")Long article_id);

    //get article_writer_id by article_id
    @Select("SELECT article_writer_id FROM article_writer WHERE article_id = #{article_id}")
    List<Long> getArticleWriterId(@Param("article_id")Long article_id);

    //get article_keyword_id by article_id
    @Select("SELECT article_keyword_id FROM article_keyword WHERE article_id = #{article_id}")
    List<Long> getArticleKeywordId(@Param("article_id")Long article_id);

    //get article_type_id by article_id
    @Select("SELECT article_type_id FROM article_type WHERE article_id = #{article_id}")
    List<Long> getArticleTypeId(@Param("article_id")Long article_id);

    //update article_id by title
    @Update("UPDATE article SET article_id = #{article_id} WHERE title = #{title}")
    void updateIdByTitle(@Param("article_id")Long article_id, @Param("title")String title);



    /*@Select("SELECT article_id, title, magazine, date, abstract_content, url, upload_time FROM article WHERE magazine = #{magazine}")
    List<ArticleListResponse> getArticleByMagazine(@Param("magazine") String magazine);

    @Select("SELECT article_id, title, magazine, date, abstract_content, url, upload_time FROM article WHERE date = #{date}")
    List<ArticleListResponse> getArticleByDate(@Param("date") String date);

    @Select("SELECT article_id, title, magazine, date, abstract_content, url, upload_time FROM article WHERE title LIKE CONCAT('%',#{title},'%')")
    List<ArticleListResponse> getArticleByTitle(@Param("title") String title);*/


}