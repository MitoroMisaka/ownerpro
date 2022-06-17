package com.ownerpro.web.controller.article;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("ArticleResponse")
public class ArticleListResponse implements Serializable
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

    @ApiModelProperty("评论数")
    private Integer comment_num;
}