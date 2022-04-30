package com.ownerpro.web.service.article.impl;

import com.ownerpro.web.mapper.ArticleMapper;
import com.ownerpro.web.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void insertArticle(String title, String magazine, Timestamp date, String url, String abstract_content, Timestamp upload_time){
        articleMapper.insertArticle(title, magazine, date, url, abstract_content, upload_time);
    }

    @Override
    public Long selectIDByTitle(String title){
        return  articleMapper.selectIDByTitle(title);
    }

    @Override
    public void insertArticleWriter(Long article_id, Long writer_id){
        articleMapper.insertArticleWriter(writer_id, article_id);
    }

    @Override
    public void insertArticleArea(Long area_id, Long article_id){
        articleMapper.insertArticleArea(area_id, article_id);
    }

    @Override
    public void insertArticleKeyword(Long keyword_id, Long article_id){
        articleMapper.insertArticleKeyword(article_id, keyword_id);
    }

    @Override
    public void insertArticleType(Long type_id, Long article_id){
        articleMapper.insertArticleType(type_id, article_id);
    }

    @Override
    public Boolean isWriterExists(String name){
        //check if the writer exist in the database
        if(articleMapper.isWriterExists(name)!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Long selectWriterIDByName(String name) {
        if (articleMapper.isWriterExists(name) != null) {
            return articleMapper.isWriterExists(name);
        }
        else {
            return null;
        }
    }

    @Override
    public Long selectKeywordIDByWord(String word){
        if(articleMapper.isWriterExists(word) != null){
            return articleMapper.isKeywordExists(word);
        }else{
            return null;
        }
    }

    @Override
    public Long selectAreaIDByName(String name){
        if(articleMapper.isAreaExists(name) != null){
            return articleMapper.isAreaExists(name);
        }
        else{
            return null;
        }
    }

    @Override
    public Long selectTypeIDByName(String name){
        if(articleMapper.isTypeExists(name) != null){
            return articleMapper.isTypeExists(name);
        }else{
            return null;
        }
    }

    @Override
    public Boolean isArticleWriterExists(Long article_id, Long writer_id){
        if (articleMapper.isArticleWriterExists(article_id, writer_id) == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean isArticleAreaExists(Long article_id, Long area_id){
        if(articleMapper.isArticleAreaExists(article_id, area_id) == null ){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean isArticleTypeExists(Long article_id, Long type_id){
        if(articleMapper.isArticleTypeExists(article_id, type_id) == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean isArticleKeywordExists(Long article_id, Long keyword_id){
        if(articleMapper.isArticleKeywordExists(article_id, keyword_id) == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void insertWriter(String name){
        articleMapper.insertWriter(name);
    }

    @Override
    public void insertArea(String name){
        articleMapper.insertArea(name);
    }

    @Override
    public void insertKeyword(String word){
        articleMapper.insertKeyword(word);
    }

    @Override
    public void insertType(String name){
        articleMapper.insertType(name);
    }

    @Override
    public Boolean isKeywordExists(String name){
        if(articleMapper.isKeywordExists(name) == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean isAreaExists(String name){
        if(articleMapper.isAreaExists(name)==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean isTypeExists(String name){
        if(articleMapper.isTypeExists(name) == null){
            return false;
        }else{
            return true;
        }
    }
}
