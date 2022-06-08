package com.ownerpro.web.service.article.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ownerpro.web.common.Page;
import com.ownerpro.web.common.PageParam;
import com.ownerpro.web.common.Result;
import com.ownerpro.web.controller.article.ArticleListResponse;
import com.ownerpro.web.controller.article.ArticleResponse;
import com.ownerpro.web.controller.comment.CommentResponse;
import com.ownerpro.web.entity.Comment;
import com.ownerpro.web.entity.Reference;
import com.ownerpro.web.mapper.ArticleMapper;
import com.ownerpro.web.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Page<ArticleListResponse> getAllArticles(Integer pageNum, Integer pageSize, String order){
        //use startpage to page
        PageHelper.startPage(pageNum, pageSize, order);
        List<ArticleListResponse> articleListResponseList =  articleMapper.getArticleList();
        return new Page<>(new PageInfo<>(articleListResponseList));
    }

    @Override
    public ArticleResponse selectArticleById(Long article_id){
        ArticleListResponse articleListResponse = articleMapper.getArticleById(article_id);
        //get and add article_id title magazine date abstract_content url into articleResponse
        List<String> Writer = articleMapper.getWriterByArticleId(article_id);
        List<String> keyword = articleMapper.getKeywordByArticleId(article_id);
        List<String> area = articleMapper.getAreaByArticleId(article_id);
        List<String> type = articleMapper.getTypeByArticleId(article_id);
        List<String> reference = articleMapper.getReferenceByArticleId(article_id);
        return new ArticleResponse(articleListResponse.getArticle_id(), articleListResponse.getTitle(), articleListResponse.getMagazine(), articleListResponse.getDate(),
                articleListResponse.getAbstract_content(), articleListResponse.getUrl(),articleListResponse.getUpload_time(), Writer, keyword, area, type, reference);
    }

    @Override
    public Page<Reference> getAllReferences(PageParam pageParam){
        int pageNum = pageParam.getPageNum();
        int pageSize = pageParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Reference> referenceList = articleMapper.getAllReferences();
        return new Page<>(new PageInfo<>(referenceList));
    }
    //About the notes
    @Override
    public void addNote(Long article_id, String content, String publisher){
        articleMapper.addNote(article_id, content , publisher);
    }

    @Override
    public String getNameByUsername(String username){
        return articleMapper.getNameByUsername(username);
    }

    @Override
    public Long getIdByUsername(String username){
        return articleMapper.getIdByUsername(username);
    }

    @Override
    public void addComment(Long note_id, String content, Long super_id, Long id, Timestamp comment_time, String name){
        articleMapper.addComment(comment_time, content, id, note_id, super_id, name);
    }

    @Override
    public Page<CommentResponse> getComment(PageParam pageParam, Long id){
        //get the comment and the subcomments
        int pageNum = pageParam.getPageNum();
        int pageSize = pageParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = articleMapper.getMainComment(id);
        List<CommentResponse> commentResponseList = new ArrayList<>();
        //add element to the List
        for(Comment comment : commentList){
            List<Comment> subComment = articleMapper.getSubComment(comment.getComment_id());
            commentResponseList.add(new CommentResponse(comment, subComment));
        }
        return new Page<>(new PageInfo<>(commentResponseList));
    }

    //delete from article , article_keyword , article_type, article_area, article_writer , comment, note, reference where id = #{id}
    @Override
    public Result deleteArticle(Long id){
        try {
            articleMapper.deleteArticle(id);
            articleMapper.deleteArticleKeyword(id);
            articleMapper.deleteArticleType(id);
            articleMapper.deleteArticleArea(id);
            articleMapper.deleteArticleWriter(id);
            articleMapper.deleteComment(id);
            articleMapper.deleteNote(id);
            articleMapper.deleteArticleReference(id);
        }catch (Exception e){
            return Result.fail("删除失败");
        }
        return Result.success("删除成功");
    }

    @Override
    public Result deleteComment(Long id){
        try {
            articleMapper.deleteCommentById(id);
        }catch (Exception e){
            return Result.fail("删除失败");
        }
        return Result.success("删除成功");
    }

    //delete type by type_id
    @Override
    public Result deleteType(Long id){
        try {
            articleMapper.deleteTypeById(id);
        }catch (Exception e){
            return Result.fail("删除失败");
        }
        return Result.success("删除成功");
    }

    //delete area by area_id
    @Override
    public Result deleteArea(Long area_id){
        try {
            articleMapper.deleteAreaById(area_id);
        }catch (Exception e){
            return Result.fail("删除失败");
        }
        return Result.success("删除成功");
    }

    //delete writer by writer_id
    @Override
    public Result deleteWriter(Long writer_id){
        try {
            articleMapper.deleteWriterById(writer_id);
        }catch (Exception e){
            return Result.fail("删除失败");
        }
        return Result.success("删除成功");
    }

    //delete keyword by keyword_id
    @Override
    public Result deleteKeyword(Long keyword_id){
        try {
            articleMapper.deleteKeywordById(keyword_id);
        }catch (Exception e){
            return Result.fail("删除失败");
        }
        return Result.success("删除成功");
    }

    //delete reference by reference_id
    @Override
    public Result deleteReference(Long reference_id){
        try {
            articleMapper.deleteReferenceById(reference_id);
        }catch (Exception e){
            return Result.fail("删除失败");
        }
        return Result.success("删除成功");
    }

    //delete note by note_id
    @Override
    public Result deleteNote(Long note_id){
        try {
            articleMapper.deleteNoteById(note_id);
        }catch (Exception e){
            return Result.fail("删除失败");
        }
        return Result.success("删除成功");
    }
}
