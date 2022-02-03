package com.ownerpro.web.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("UpdateUserMessageRequest 修改用户信息")
public class UpdateUserMessageRequest {
    @ApiModelProperty("状态")
    private Short status;
}
