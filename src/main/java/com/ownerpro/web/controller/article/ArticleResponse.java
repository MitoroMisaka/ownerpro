package com.ownerpro.web.controller.article;


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
    private Timestamp date;

    @ApiModelProperty("主要内容")
    private String abstract_content;

    @ApiModelProperty("链接")
    private String url;

    @ApiModelProperty("上传时间")
    private Timestamp upload_time;

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

    public ArticleResponse(Long article_id, String title, String magazine, Timestamp date, String abstract_content, String url, Timestamp upload_time, List<String> writer, List<String> type, List<String> area, List<String> keyword, List<String> reference) {
        this.article_id = article_id;
        this.title = title;
        this.magazine = magazine;
        this.date = date;
        this.abstract_content = abstract_content;
        this.url = url;
        this.upload_time = upload_time;
        this.writer = writer;
        this.type = type;
        this.area = area;
        this.keyword = keyword;
        this.reference = reference;
    }
}