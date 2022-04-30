package com.ownerpro.web.service.article;


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
}
