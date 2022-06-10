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
@ApiModel("Note entity")
public class Note implements Serializable
{
    @Id
    @ApiModelProperty("id")
    private Long note_id;

    @ApiModelProperty("论文id")
    private Long article_id;

    @ApiModelProperty("笔记内容")
    private String content;

    @ApiModelProperty("发布者")
    private String publisher;

    @ApiModelProperty("发布时间")
    private String publish_time;
}