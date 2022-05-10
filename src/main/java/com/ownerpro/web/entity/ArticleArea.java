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
@ApiModel("ArticleArea")
public class ArticleArea implements Serializable
{
    @Id
    @ApiModelProperty("id")
    private Long article_area_id;

    @ApiModelProperty("论文id")
    private Long article_id;

    @ApiModelProperty("领域id")
    private Long area_id;
}