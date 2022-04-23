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
@ApiModel("rights entity")
public class Rights implements Serializable
{
    @Id
    @ApiModelProperty("id")
    private Long rights_id;

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("评论权限")
    private int comment;

    @ApiModelProperty("删除权限")
    private int delete;

    @ApiModelProperty("读权限")
    private int read;

    @ApiModelProperty("更新权限")
    private int update;
}