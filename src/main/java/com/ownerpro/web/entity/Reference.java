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
@ApiModel("Reference entity")
public class Reference implements Serializable
{
    @Id
    @ApiModelProperty("id")
    private Long reference_id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("链接")
    private String url;
}