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
@ApiModel("NoteRequest")
public class NoteRequest implements Serializable
{
    @ApiModelProperty("论文id")
    private Long article_id;

    @ApiModelProperty("笔记内容")
    private String content;
}