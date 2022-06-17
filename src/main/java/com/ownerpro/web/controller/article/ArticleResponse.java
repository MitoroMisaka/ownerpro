package com.ownerpro.web.controller.article;


import com.ownerpro.web.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("ArticleResponse")
public class ArticleResponse implements Serializable
{
    @ApiModelProperty("id")
    private Long article_id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("刊会")
    private String magazine;

    @ApiModelProperty("刊会时间")
    private String date;

    @ApiModelProperty("主要内容")
    private String abstract_content;

    @ApiModelProperty("链接")
    private String url;

    @ApiModelProperty("上传时间")
    private String upload_time;

    @ApiModelProperty("作者数组")
    private List<String> writer;

    @ApiModelProperty("文章类型数组")
    private List<String> type;

    @ApiModelProperty("文章领域数组")
    private List<String> area;

    @ApiModelProperty("文章关键字数组")
    private List<String> keyword;

    @ApiModelProperty("参考文献数组")
    private List<String> reference;



    public ArticleResponse(ArticleListResponse articleListResponse, List<String> writer,  List<String> type, List<String> area, List<String> keyword, List<String> reference){
        this.article_id = articleListResponse.getArticle_id();
        this.abstract_content = articleListResponse.getAbstract_content();
        this.title = articleListResponse.getTitle();
        this.magazine = articleListResponse.getMagazine();
        this.date = articleListResponse.getDate();
        this.url = articleListResponse.getUrl();
        this.upload_time = articleListResponse.getUpload_time();
        this.writer = writer;
        this.type = type;
        this.area = area;
        this.keyword = keyword;
    }

    public ArticleResponse(Article article, List<String> writer, List<String> type, List<String> area, List<String> keyword, List<String> reference){
        this.article_id = article.getArticle_id();
        this.abstract_content = article.getAbstract_content();
        this.title = article.getTitle();
        this.magazine = article.getMagazine();
        this.date = article.getDate();
        this.url = article.getUrl();
        this.upload_time = article.getUpload_time();
        this.writer = writer;
        this.type = type;
        this.area = area;
        this.keyword = keyword;
        this.reference = reference;
    }
}