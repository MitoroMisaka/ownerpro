package com.ownerpro.web.dto;


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
@ApiModel("NoteUpdateRequest")
public class NoteUpdateRequest implements Serializable
{
    @ApiModelProperty("笔记id")
    private Long note_id;

    @ApiModelProperty("论文id")
    private Long article_id;

    @ApiModelProperty("笔记内容")
    private String content;

}
