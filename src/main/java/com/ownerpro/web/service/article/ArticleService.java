package com.ownerpro.web.service.article;


import com.ownerpro.web.common.Page;
import com.ownerpro.web.common.PageParam;
import com.ownerpro.web.common.Result;
import com.ownerpro.web.controller.article.ArticleListResponse;
import com.ownerpro.web.controller.article.ArticleResponse;
import com.ownerpro.web.controller.comment.CommentResponse;
import com.ownerpro.web.entity.Comment;
import com.ownerpro.web.entity.Note;
import com.ownerpro.web.entity.Reference;


import java.sql.Time;
import java.sql.Timestamp;

public interface ArticleService {
    void insertArticle(String title, String magazine,  Timestamp date, String url,String abstract_content, Timestamp upload_time);

    Long selectIDByTitle(String title);

    void insertArticleWriter(Long article_id, Long writer_id);

    void insertArticleArea(Long area_id, Long article_id);

    void insertArticleKeyword(Long Keyword_id, Long article_id);

    void insertArticleType(Long type_id, Long article_id);

    Boolean isWriterExists(String name);

    Boolean isKeywordExists(String word);

    Boolean isAreaExists(String name);

    Boolean isTypeExists(String name);

    void insertWriter(String name);

    void insertArea(String name);

    void insertKeyword(String word);

    void insertType(String name);

    Long selectWriterIDByName(String name);

    Long selectKeywordIDByWord(String name);

    Long selectTypeIDByName(String name);

    Long selectAreaIDByName(String name);

    Boolean isArticleWriterExists(Long article_id, Long writer_id);

    Boolean isArticleAreaExists(Long article_id, Long area_id);

    Boolean isArticleTypeExists(Long article_id, Long type_id);

    Boolean isArticleKeywordExists(Long article_id, Long keyword_id);

    Page<ArticleListResponse> getAllArticles(Integer pageNum, Integer pageSize, String order);

    ArticleResponse selectArticleById(Long article_id);

    Page<Reference> getAllReferences(PageParam pageParam);
    //About the note
    void addNote(Long article_id, String content, String publisher);

    String getNameByUsername(String username);

    Long getIdByUsername(String username);

    void addComment(Long note_id, String content, Long super_id, Long id, Timestamp comment_time, String name );

    Page<CommentResponse> getComment(PageParam pageParam, Long id);

    Result deleteArticle(Long article_id);

    Result deleteComment(Long comment_id);

    //delete type by type_id
    Result deleteType(Long type_id);

    //delete area by area_id
    Result deleteArea(Long area_id);

    //delete keyword by keyword_id
    Result deleteKeyword(Long keyword_id);

    //delete writer by writer_id
    Result deleteWriter(Long writer_id);

    //delete reference by reference_id
    Result deleteReference(Long reference_id);

    //delete note by note_id
    Result deleteNote(Long note_id);

    Page<Note> getNotes(Long article_id, int pageNum, int pageSize, String orderBy);
}
