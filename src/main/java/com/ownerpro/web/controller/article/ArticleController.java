package com.ownerpro.web.controller.article;


import com.ownerpro.web.common.Result;
import com.ownerpro.web.service.article.ArticleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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


    @PostMapping("/add")
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


}

