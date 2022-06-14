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
import com.ownerpro.web.entity.User;
import com.ownerpro.web.mapper.*;
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

    @Autowired
    UserDTOMapper userDTOMapper;

    @Autowired
    UserMapper userMapper;

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
    public Object searchByLabel(String content,String label_content, String label, Integer pageSize, Integer pageNum, String orderBy) {
        //divide into 4 search by the label of writer keyword type and area
        if(label.equals("writer")){
            return searchByWriter(content,label_content, pageSize, pageNum, orderBy);
        }
        else if(label.equals("keyword")){
            return searchByKeyword(content,label_content, pageSize, pageNum, orderBy);
        }
        else if(label.equals("type")){
            return searchByType(content,label_content, pageSize, pageNum, orderBy);
        }
        else if(label.equals("area")){
            return searchByArea(content,label_content, pageSize, pageNum, orderBy);
        }
        return Result.fail("label error");
    }

    //search by writer
    @Override
    public Page<Article> searchByWriter(String content,String label_content, Integer pageSize, Integer pageNum, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Article> articleList = searchMapper.searchByWriter(content, label_content);
        return new Page<>(pageNum, pageSize, new PageInfo(articleList).getTotal(), new PageInfo(articleList).getPages(), articleList);
    }

    //search by keyword
    @Override
    public Page<Article> searchByKeyword(String content,String label_content, Integer pageSize, Integer pageNum, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Article> articleList = searchMapper.searchByKeyword(content, label_content);
        return new Page<>(pageNum, pageSize, new PageInfo(articleList).getTotal(), new PageInfo(articleList).getPages(), articleList);
    }

    //search by area
    @Override
    public Page<Article> searchByArea(String content,String label_content, Integer pageSize, Integer pageNum, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Article> articleList = searchMapper.searchByArea(content, label_content);
        return new Page<>(pageNum, pageSize, new PageInfo(articleList).getTotal(), new PageInfo(articleList).getPages(), articleList);
    }

    //search by type
    @Override
    public Page<Article> searchByType(String content,String label_content, Integer pageSize, Integer pageNum, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Article> articleList = searchMapper.searchByType(content, label_content);
        return new Page<>(pageNum, pageSize, new PageInfo(articleList).getTotal(), new PageInfo(articleList).getPages(), articleList);
    }

    @Override
    public Page<User> searchUser(String content, Integer pageSize, Integer pageNum, String orderBy) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("username", "%" + content + "%");
        criteria.orLike("name", "%" + content + "%");
        example.and(criteria);
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<User> userList = userMapper.selectByExample(example);
        return new Page<>(pageNum, pageSize, new PageInfo(userList).getTotal(), new PageInfo(userList).getPages(), userList);
    }
}
