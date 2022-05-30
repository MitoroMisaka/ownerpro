package com.ownerpro.web.service.search.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ownerpro.web.common.Page;
import com.ownerpro.web.common.PageParam;
import com.ownerpro.web.common.Result;
import com.ownerpro.web.controller.article.ArticleListResponse;
import com.ownerpro.web.dto.UserDTO;
import com.ownerpro.web.entity.Article;
import com.ownerpro.web.entity.SearchRecord;
import com.ownerpro.web.mapper.ArticleListMapper;
import com.ownerpro.web.mapper.ArticleMapper;
import com.ownerpro.web.mapper.SearchMapper;
import com.ownerpro.web.mapper.SearchRecordMapper;
import com.ownerpro.web.service.search.SearchService;
import com.ownerpro.web.util.PasswordUtil;
import com.ownerpro.web.util.TimeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleListMapper articleListMapper;

    @Autowired
    SearchMapper searchMapper;

    @Autowired
    SearchRecordMapper searchRecordMapper;

    @Override
    public Page<Article> searchTitle(String title, Integer pageSize, Integer pageNum, String orderBy) {
        Example example = new Example(Article.class);
        //example.selectProperties("title","magazine","abstract_content");

        if(!StringUtils.isEmpty(title)){
            Example.Criteria criteria = example.createCriteria();
            criteria.orLike("title", "%" + title + "%");
            criteria.orLike("magazine", "%" + title + "%");
            //abstract_content
            criteria.orLike("abstract_content", "%" + title + "%");
            example.and(criteria);
        }


        UserDTO principle = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        if(principle != null) {
            String username = principle.getUsername();
            Long id = articleMapper.getIdByUsername(username);

            SearchRecord searchRecord = SearchRecord.builder()
                    .content(title)
                    .id(id)
                    .time(TimeUtil.getCurrentTimestamp())
                    .build();
            searchRecordMapper.insert(searchRecord);
        }
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Article> articleList = articleMapper.selectByExample(example);
        Page page = new Page(new PageInfo(articleList));

        ArrayList<Article> articleArrayList = new ArrayList<>();
        for (Article article : articleList) {
            //add article_id title magazine date abstract_content url upload_time and comment_num
            articleArrayList.add(new Article(article.getArticle_id(), article.getTitle(),
                    article.getMagazine(), article.getDate(),
                    article.getAbstract_content(), article.getUrl(),
                    article.getUpload_time(), article.getComment_num()));
        }
        return new Page<>(pageNum, pageSize, page.getTotal(), page.getPages(), articleArrayList);
    }

    @Override
    public List<String> getSearchRecord(Integer id){
        return searchMapper.getRecord(id);
    }

    @Override
    public Object searchByLabel(String content, String label, Integer pageSize, Integer pageNum, String orderBy) {
        //divide into 4 search by the label of writer keyword type and area
        if(label.equals("writer")){
            return searchByWriter(content, pageSize, pageNum, orderBy);
        }
        else if(label.equals("keyword")){
            return searchByKeyword(content, pageSize, pageNum, orderBy);
        }
        else if(label.equals("type")){
            return searchByType(content, pageSize, pageNum, orderBy);
        }
        else if(label.equals("area")){
            return searchByArea(content, pageSize, pageNum, orderBy);
        }
        return Result.fail("label error");
    }

    //search by writer
    @Override
    public Page<Article> searchByWriter(String content, Integer pageSize, Integer pageNum, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Article> articleList = searchMapper.searchByWriter(content);
        return new Page<>(pageNum, pageSize, new PageInfo(articleList).getTotal(), new PageInfo(articleList).getPages(), articleList);
    }

    //search by keyword
    @Override
    public Page<Article> searchByKeyword(String content, Integer pageSize, Integer pageNum, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Article> articleList = searchMapper.searchByKeyword(content);
        return new Page<>(pageNum, pageSize, new PageInfo(articleList).getTotal(), new PageInfo(articleList).getPages(), articleList);
    }

    //search by area
    @Override
    public Page<Article> searchByArea(String content, Integer pageSize, Integer pageNum, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Article> articleList = searchMapper.searchByArea(content);
        return new Page<>(pageNum, pageSize, new PageInfo(articleList).getTotal(), new PageInfo(articleList).getPages(), articleList);
    }

    //search by type
    @Override
    public Page<Article> searchByType(String content, Integer pageSize, Integer pageNum, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Article> articleList = searchMapper.searchByType(content);
        return new Page<>(pageNum, pageSize, new PageInfo(articleList).getTotal(), new PageInfo(articleList).getPages(), articleList);
    }
}
