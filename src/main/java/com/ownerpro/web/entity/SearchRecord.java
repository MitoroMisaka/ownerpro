package com.ownerpro.web.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("search_history entity")
public class SearchRecord implements Serializable
{
    @Id
    @ApiModelProperty("id")
    private Long history_id;

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("时间")
    private Timestamp time;
}