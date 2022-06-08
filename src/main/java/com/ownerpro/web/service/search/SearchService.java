package com.ownerpro.web.service.search;

import com.ownerpro.web.common.Page;
import com.ownerpro.web.controller.article.ArticleListResponse;
import com.ownerpro.web.entity.Article;
import com.ownerpro.web.entity.SearchRecord;

import java.util.List;

public interface SearchService {
    Page<Article> searchTitle(String title, Integer pageSize, Integer pageNum, String orderBy);
    List<String> getSearchRecord(Integer id);
    Object searchByLabel(String content,String label_content, String label, Integer pageSize, Integer pageNum, String orderBy);
    //search by writer
    Page<Article> searchByWriter(String content,String label_content, Integer pageSize, Integer pageNum, String orderBy);
    //search by keyword
    Page<Article> searchByKeyword(String content,String label_content, Integer pageSize, Integer pageNum, String orderBy);
    //search by type
    Page<Article> searchByType(String content,String label_content, Integer pageSize, Integer pageNum, String orderBy);
    //search by area
    Page<Article> searchByArea(String content,String label_content, Integer pageSize, Integer pageNum, String orderBy);
}
