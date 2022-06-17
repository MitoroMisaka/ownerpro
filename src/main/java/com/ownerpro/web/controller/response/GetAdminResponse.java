package com.ownerpro.web.controller.response;


import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("GetAdminrResponse 管理员信息返回")
public class GetAdminResponse implements Serializable {
    @ApiModelProperty("用户id")
    private Long id;

    private String name;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("类别")
    private Integer type;

    private String avatar;

    public GetAdminResponse(Admin admin, Integer type){
        this.id = admin.getId();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
        this.type = type;
        this.avatar = admin.getAvatar();
        this.name = admin.getName();
    }
}
