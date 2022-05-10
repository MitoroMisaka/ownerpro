package com.ownerpro.web.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("comment entity")
public class Comment implements Serializable
{
    @Id
    @ApiModelProperty("id")
    private Long comment_id;

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("论文id")
    private Long article_id;

    @ApiModelProperty("评论时间")
    private String comment_time;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("点赞数")
    private int likes;

    @ApiModelProperty("树形评论id")
    private Long super_id;

    @ApiModelProperty("姓名")
    private String name;
}