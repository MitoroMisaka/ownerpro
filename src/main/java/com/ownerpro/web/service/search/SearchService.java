package com.ownerpro.web.service.search;

import com.ownerpro.web.common.Page;
import com.ownerpro.web.controller.article.ArticleListResponse;
import com.ownerpro.web.entity.Article;
import com.ownerpro.web.entity.SearchRecord;

import java.util.List;

public interface SearchService {
    Page<Article> searchTitle(String title, Integer pageSize, Integer pageNum, String orderBy);
    List<String> getSearchRecord(Integer id);
    Object searchByLabel(String content, String label, Integer pageSize, Integer pageNum);
    //search by writer
    Page<Article> searchByWriter(String content, Integer pageSize, Integer pageNum);
    //search by keyword
    Page<Article> searchByKeyword(String content, Integer pageSize, Integer pageNum);
    //search by type
    Page<Article> searchByType(String content, Integer pageSize, Integer pageNum);
    //search by area
    Page<Article> searchByArea(String content, Integer pageSize, Integer pageNum);
}
