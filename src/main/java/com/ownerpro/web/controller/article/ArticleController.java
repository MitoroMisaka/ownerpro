package com.ownerpro.web.controller.article;


import com.ownerpro.web.common.PageParam;
import com.ownerpro.web.common.Result;
import com.ownerpro.web.controller.comment.CommentRequest;
import com.ownerpro.web.dto.NoteRequest;
import com.ownerpro.web.dto.UserDTO;
import com.ownerpro.web.mapper.ArticleMapper;
import com.ownerpro.web.mapper.NoteMapper;
import com.ownerpro.web.service.article.ArticleService;
import com.ownerpro.web.util.SessionUtil;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;


@RestController
@Api("论文Controller")
@Validated
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    NoteMapper noteMapper;

    @PostMapping("/addArticle")
    @ApiOperation(value = "添加文章", notes = "添加文章")
    public Result addArticle(@ApiParam @RequestBody ArticleRequest articleRequest) throws ParseException {
        //add basic info of article
        String title = articleRequest.getTitle();
        String magazine = articleRequest.getMagazine();
        String date = articleRequest.getDate();
        String url = articleRequest.getUrl();
        String abstract_content = articleRequest.getAbstract_content();
        if(StringUtils.isEmpty(title) ||  StringUtils.isEmpty(magazine) ||
                StringUtils.isEmpty(date) || StringUtils.isEmpty(url) || StringUtils.isEmpty(abstract_content)){
            return Result.fail("标题，作者，发布会议，日期，文献链接，主要内容不能为空");
        }
        DateFormat df = new SimpleDateFormat("dd-MM-yy");
        //convert date to timestamp
        /*try {
            Timestamp date1 = new Timestamp(df.parse(date).getTime());
        } catch (ParseException e) {
            return Result.fail("日期格式错误");
        }*/
        Timestamp date1 = new Timestamp(df.parse(date).getTime());
        Timestamp date2 = new Timestamp(System.currentTimeMillis());
        try{
            //add article
            articleService.insertArticle(title, magazine, date1, url, abstract_content, date2);
        }catch (Exception e){
            return Result.fail("添加文章失败");
        }
        Long article_id = articleService.selectIDByTitle(title);
        //add article_writer
        List<String> writer = ArticleRequest.class.cast(articleRequest).getWriter();
        for(Iterator<String> it = writer.iterator(); it.hasNext();){
            //if writer is not exist, add it
            String name = it.next();
            if(!articleService.isWriterExists(name)){
                articleService.insertWriter(name);
            }
            Long writer_id = articleService.selectWriterIDByName(name);
            if(!articleService.isArticleWriterExists(article_id, writer_id)) {
                articleService.insertArticleWriter(article_id, writer_id);
            }
        }
        //add article_area
        List<String> area = ArticleRequest.class.cast(articleRequest).getArea();
        for(Iterator<String> it = area.iterator(); it.hasNext();){
            //if area is not exist, add it
            String name = it.next();
            if(!articleService.isAreaExists(name)){
                articleService.insertArea(name);
            }
            Long area_id = articleService.selectAreaIDByName(name);
            if(!articleService.isArticleAreaExists(article_id, area_id)) {
                articleService.insertArticleArea(article_id, area_id);
            }
        }
        //add article_keyword
        List<String> keyword = ArticleRequest.class.cast(articleRequest).getKeyword();
        for(Iterator<String> it = keyword.iterator(); it.hasNext();){
            //if keyword is not exist, add it
            String name = it.next();
            if(!articleService.isKeywordExists(name)){
                articleService.insertKeyword(name);
            }
            Long keyword_id = articleService.selectKeywordIDByWord(name);
            if(!articleService.isArticleKeywordExists(article_id, keyword_id)) {
                articleService.insertArticleKeyword(article_id, keyword_id);
            }
        }
        //add article_type
        List<String> type = ArticleRequest.class.cast(articleRequest).getType();
        for(Iterator<String> it = type.iterator(); it.hasNext();){
            //if type is not exist, add it
            String name = it.next();
            if(!articleService.isTypeExists(name)){
                articleService.insertType(name);
            }
            Long type_id = articleService.selectTypeIDByName(name);
            if(!articleService.isArticleTypeExists(article_id, type_id)) {
                articleService.insertArticleType(article_id, type_id);
            }
        }
        return Result.success("添加成功！");
    }

    @GetMapping("/get_all_article")
    @ApiOperation(value = "获取所有文章", notes = "获取所有文章")
    @ApiImplicitParams({
            //get pageSize, pageNum, order
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "页面起始", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "orderBy", value = "排序规则（'id asc'格式,id是排序字段，asc/desc是升序降序）", paramType = "query", dataType = "Integer")
    })
    public Object getAllArticle(@NotNull @RequestParam(value = "pageSize") Integer pageSize,
                                @NotNull @RequestParam(value = "pageNum") Integer pageNum,
                                @NotNull @RequestParam(value = "orderBy") String orderBy) {
        return articleService.getAllArticles(pageNum, pageSize, orderBy);
    }

    @GetMapping("/get_article_info")
    @ApiOperation(value = "获取文章信息", notes = "获取文章信息")
    @ApiModelProperty()
    public ArticleResponse getArticleInfo(@Validated @NotNull Long article_id){
        try {
        return articleService.selectArticleById(article_id);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/get_all_reference")
    @ApiOperation(value = "获取所有引用", notes = "获取所有引用")
    public Object getAllReference(@NotNull @RequestBody PageParam pageParam){
        return articleService.getAllReferences(pageParam);
    }

    @PostMapping("/add_note")
    @ApiOperation(value = "添加笔记", notes = "添加笔记")
    public Result addNote(@RequestBody NoteRequest noteRequest){
        Long article_id = noteRequest.getArticle_id();
        String content = noteRequest.getContent();
        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        String username = principal.getUsername();
        String publisher = articleService.getNameByUsername(username);
        articleService.addNote(article_id, content, publisher);
        return Result.success("添加成功！");
    }

    @PostMapping("/add_note1")
    @ApiOperation(value = "添加笔记", notes = "添加笔记")
    public int addNote1(@RequestBody Bean bean){
        return noteMapper.add(bean);
    }

    @PostMapping("/add_comment")
    @ApiOperation(value = "添加评论", notes = "添加评论")
    public Result addComment(@RequestBody CommentRequest commentRequest){
        Long note_id = commentRequest.getNote_id();
        String content = commentRequest.getContent();
        Long super_id = commentRequest.getSuper_id();
        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        Long user_id = articleService.getIdByUsername(principal.getUsername());
        String name = articleService.getNameByUsername(principal.getUsername());
        //get the time now
        Timestamp time = new Timestamp(System.currentTimeMillis());
        articleService.addComment(note_id, content, super_id, user_id, time, name);
        return Result.success("添加成功！");
    }

    @GetMapping("/get_comment/{id}")
    @ApiOperation(value = "获取评论", notes = "获取评论")
    public Object getComment(@NotNull @RequestBody PageParam pageParam, @PathVariable Long id){
        return articleService.getComment(pageParam, id);
    }
}

