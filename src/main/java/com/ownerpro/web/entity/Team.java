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
@ApiModel("Team 团队")
public class Team implements Serializable
{
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Short age;

    @ApiModelProperty("爱好")
    private String hobby;

    @ApiModelProperty("联系方式")
    private String contact;

    @ApiModelProperty("自我介绍")
    private String introduction;
}
