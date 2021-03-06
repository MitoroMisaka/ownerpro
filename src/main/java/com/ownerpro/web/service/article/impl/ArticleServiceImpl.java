package com.ownerpro.web.service.article.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ownerpro.web.common.Page;
import com.ownerpro.web.common.PageParam;
import com.ownerpro.web.common.Result;
import com.ownerpro.web.controller.article.ArticleListResponse;
import com.ownerpro.web.controller.article.ArticleResponse;
import com.ownerpro.web.controller.comment.CommentMain;
import com.ownerpro.web.controller.comment.CommentResponse;
import com.ownerpro.web.dto.UserComment;
import com.ownerpro.web.entity.Comment;
import com.ownerpro.web.entity.Note;
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
        articleMapper.insertArticleKeyword(keyword_id, article_id);
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
    public Page<ArticleResponse> getAllArticles(Integer pageNum, Integer pageSize, String order){
        List<ArticleResponse> finalArticlesList = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize, order);
        List<ArticleListResponse> articleListResponseList =  articleMapper.getArticleList();
        for(ArticleListResponse articleListResponse : articleListResponseList){
            Long article_id = articleListResponse.getArticle_id();
            List<String> Writer = articleMapper.getWriterByArticleId(article_id);
            List<String> keyword = articleMapper.getKeywordByArticleId(article_id);
            List<String> area = articleMapper.getAreaByArticleId(article_id);
            List<String> type = articleMapper.getTypeByArticleId(article_id);
            List<String> reference = articleMapper.getReferenceByArticleId(article_id);
            finalArticlesList.add(new ArticleResponse(articleListResponse, Writer, keyword, area, type, reference));
        }
        return new Page<>(new PageInfo<>(finalArticlesList));
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
        System.out.println(reference);
        return new ArticleResponse(articleListResponse.getArticle_id(), articleListResponse.getTitle(), articleListResponse.getMagazine(), articleListResponse.getDate(),
                articleListResponse.getAbstract_content(), articleListResponse.getUrl(),articleListResponse.getUpload_time(), Writer, keyword, area, type, reference);
    }

    @Override
    public List<Reference> getAllReferences(){
        List<Reference> referenceList = articleMapper.getAllReferences();
        return referenceList;
    }
    //About the notes
    @Override
    public void addNote(Long article_id, String content, String publisher, Timestamp publish_time){
        articleMapper.addNote(article_id, content , publisher, publish_time);
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
    public void addComment(Long note_id, String content, Long super_id, Long id, Timestamp comment_time, String name, Long to_user){
        articleMapper.addComment(comment_time, content, id, note_id, super_id, name, to_user);
    }

//    @Override
//    public Page<CommentResponse> getComment(int pageNum, int pageSize, String orderBy, Long id){
//        PageHelper.startPage(pageNum, pageSize, orderBy);
//        List<Comment> commentList = articleMapper.getMainComment(id);
//        List<CommentResponse> commentResponseList = new ArrayList<>();
//        //add element to the List
//        for(Comment comment : commentList){
//            List<Comment> subComment = articleMapper.getSubComment(comment.getComment_id());
//            commentResponseList.add(new CommentResponse(comment, subComment));
//        }
//        return new Page<>(new PageInfo<>(commentResponseList));
//    }

    @Override
    public Page<CommentResponse> getComment(int pageNum, int pageSize, String orderBy, Long id){
        PageHelper.startPage(pageNum, pageSize, orderBy);
        //get all the comment_id where note id is note id
        List<Long> comment_id = articleMapper.getCommentId(id);
        //get CommentMain by comment_id
        //print the comment_id
        System.out.println(comment_id);
        List<CommentMain> commentMainList = new ArrayList<>();
        for(Long comment_id_temp : comment_id){
            CommentMain commentMain = articleMapper.getCommentMain(comment_id_temp);
            commentMainList.add(commentMain);
        }
        //print the commentMainList
        System.out.println(commentMainList);
        //get UserComment by name and to_user in CommentMain
        List<UserComment> userCommentList = new ArrayList<>();
        List<UserComment> to_userCommentList = new ArrayList<>();
        for(CommentMain commentMain : commentMainList){
            UserComment userComment = articleMapper.getUserComment(commentMain.getId());
            UserComment to_userComment = articleMapper.getUserComment(commentMain.getTo_user());
            userCommentList.add(userComment);
            to_userCommentList.add(to_userComment);
        }
        //print the userCommentList and to_userCommentList
        System.out.println(userCommentList);
        System.out.println(to_userCommentList);
        //merge commentMain userComment to_userComment to Comment
        List<Comment> commentList = new ArrayList<>();
        for(int i = 0; i < commentMainList.size(); i++){
            Comment comment = new Comment(commentMainList.get(i), userCommentList.get(i), to_userCommentList.get(i));
            commentList.add(comment);
        }
        //print the commentList
        System.out.println(commentList);
        //new a array of CommentResponse
        List<CommentResponse> commentResponseList = new ArrayList<>();
        for(Comment comment : commentList){
            //get all the CommentMain where their super_id = comment_id in comment
            List<CommentMain> subCommentMainList = articleMapper.getSubComment(comment.getComment_id());
            //for each subCommentMain, get UserComment by name and to_user and merge to Comment
            List<Comment> subCommentList = new ArrayList<>();
            for(CommentMain subCommentMain : subCommentMainList){
                UserComment userComment = articleMapper.getUserComment(subCommentMain.getId());
                UserComment to_userComment = articleMapper.getUserComment(subCommentMain.getTo_user());
                Comment subComment = new Comment(subCommentMain, userComment, to_userComment);
                subCommentList.add(subComment);
            }
            //merge comment and subComment to CommentResponse
            CommentResponse commentResponse = new CommentResponse(comment, subCommentList);
            //add to the array
            commentResponseList.add(commentResponse);
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
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }

    @Override
    public Result deleteMainArticle(Long article_id){
        try {
            articleMapper.deleteArticle(article_id);
            articleMapper.deleteArticleKeyword(article_id);
            articleMapper.deleteArticleType(article_id);
            articleMapper.deleteArticleArea(article_id);
            articleMapper.deleteArticleWriter(article_id);
        }catch (Exception e){
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }

    @Override
    public Result deleteComment(Long id){
        try {
            articleMapper.deleteCommentById(id);
        }catch (Exception e){
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }

    //delete type by type_id
    @Override
    public Result deleteType(Long id){
        try {
            articleMapper.deleteTypeById(id);
        }catch (Exception e){
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }

    //delete area by area_id
    @Override
    public Result deleteArea(Long area_id){
        try {
            articleMapper.deleteAreaById(area_id);
        }catch (Exception e){
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }

    //delete writer by writer_id
    @Override
    public Result deleteWriter(Long writer_id){
        try {
            articleMapper.deleteWriterById(writer_id);
        }catch (Exception e){
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }

    //delete keyword by keyword_id
    @Override
    public Result deleteKeyword(Long keyword_id){
        try {
            articleMapper.deleteKeywordById(keyword_id);
        }catch (Exception e){
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }

    //delete reference by reference_id
    @Override
    public Result deleteReference(Long reference_id){
        try {
            articleMapper.deleteReferenceById(reference_id);
        }catch (Exception e){
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }

    //delete note by note_id
    @Override
    public Result deleteNote(Long note_id){
        articleMapper.deleteNoteById(note_id);
        return Result.success("????????????");
    }

    @Override
    public Page<Note> getNotes(Long article_id, int pageNum, int pageSize, String orderBy){
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Note> noteList = articleMapper.getNotes(article_id);
        return new Page<>(new PageInfo<>(noteList));
    }

    @Override
    public Note getNoteById(Long note_id){
        return articleMapper.getNoteById(note_id);
    }

    @Override
    public Result likeNote(Long note_id){
        try {
            articleMapper.likeNote(note_id);
        }catch (Exception e){
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }

    @Override
    public Result likeComment(Long comment_id){
        try {
            articleMapper.likeComment(comment_id);
        }catch (Exception e){
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }

    @Override
    public Result insertReference(Long article_id, Long reference_id, String note){
        articleMapper.insertReference(article_id, reference_id, note);
        return Result.success("????????????");
    }

    //update
    @Override
    public void updateArticle(Long article_id, String title, String magazine,  Timestamp date, String url,String abstract_content, Timestamp upload_time){
        articleMapper.updateArticle(article_id,title, magazine, date, url, abstract_content, upload_time);
    }

    @Override
    public void updateArticleKeyword(Long article_id, Long keyword_id, Long article_keyword_id){
        articleMapper.updateArticleKeyword(article_id, keyword_id, article_keyword_id);
    }

    @Override
    public void updateArticleType(Long article_id, Long type_id, Long article_type_id){
        articleMapper.updateArticleType(article_id, type_id, article_type_id);
    }

    @Override
    public void updateArticleArea(Long article_id, Long area_id, Long article_area_id){
        articleMapper.updateArticleArea(article_id, area_id, article_area_id);
    }

    @Override
    public void updateArticleWriter(Long article_id, Long writer_id, Long article_writer_id){
        articleMapper.updateArticleWriter(article_id, writer_id, article_writer_id);
    }

    @Override
    public Result updateNote(Long note_id , Long article_id, String content) {
        //get the time now
        Timestamp time = new Timestamp(System.currentTimeMillis());
        try {
            articleMapper.updateNote(note_id, article_id, content, time);
        } catch (Exception e) {
            return Result.fail("????????????");
        }
        return Result.success("????????????");
    }


}
