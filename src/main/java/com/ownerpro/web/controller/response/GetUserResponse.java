package com.ownerpro.web.controller.response;

import com.ownerpro.web.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("GetUserResponse 用户信息返回")
public class GetUserResponse implements Serializable {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("类别")
    private String  type;

    private String avatar;

    private Integer select;

    private Integer update;

    private Integer delete;

    private Integer insert;


    public GetUserResponse(User user, Integer type){
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.select = user.getSelect_set();
        this.update = user.getUpdate_set();
        this.delete = user.getDelete_set();
        this.insert = user.getInsert_set();
        this.avatar = user.getAvatar();
        if(type == 0)
            this.type = "用户";
        else if(type == 1)
            this.type = "管理员";
    }
}
