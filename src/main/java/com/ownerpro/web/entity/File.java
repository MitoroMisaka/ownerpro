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
@ApiModel("File entity")
public class File implements Serializable
{
    @Id
    @ApiModelProperty("id")
    private Long file_id;

    @ApiModelProperty("论文id")
    private Long article_id;

    @ApiModelProperty("文件内容")
    private String content;

    @ApiModelProperty("文件来自链接")
    private String url;

    @ApiModelProperty("文件名")
    private String name;

    @ApiModelProperty("文件来自(人)")
    private String from;
}