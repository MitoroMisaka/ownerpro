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
@ApiModel("Keyword")
public class Keyword implements Serializable
{
    @Id
    @ApiModelProperty("id")
    private Long keyword_id;

    @ApiModelProperty("关键字")
    private String word;
}