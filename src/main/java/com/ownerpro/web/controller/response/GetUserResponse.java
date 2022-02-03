package com.ownerpro.web.controller.response;

import com.ownerpro.web.entity.User;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("GetUserResponse 用户信息返回")
public class GetUserResponse implements Serializable {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("状态")
    private Short status;

    @ApiModelProperty("注册日期")
    private Timestamp signDate;

    @ApiModelProperty("类别")
    private Integer type;

    public GetUserResponse(User user, Integer type){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.signDate = user.getSignDate();
        this.status = user.getStatus();
        this.type = type;
    }
}
