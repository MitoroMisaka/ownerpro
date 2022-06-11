package com.ownerpro.web.controller.article;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("ArticleUpdateRequest")
public class ArticleUpdateRequest implements Serializable
{
    @Id
    @ApiModelProperty("文章id")
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

    @ApiModelProperty("作者数组")
    private List<String> writer;

    @ApiModelProperty("文章类型数组")
    private List<String> type;

    @ApiModelProperty("文章领域数组")
    private List<String> area;

    @ApiModelProperty("文章关键字数组")
    private List<String> keyword;

    List<String> getWriter(){
        return this.writer;
    }
}