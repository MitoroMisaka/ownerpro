package com.phoenix.logistics.controller.response;

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
@ApiModel("BriefUserOrder 用户订单列表")
public class BriefUserOrder implements Serializable {
    @ApiModelProperty("用户订单编号")
    private Long adminOrderId;

    @ApiModelProperty("相关人用户名")
    private String relateUsername;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("状态更新时间")
    private String statusUpdateTime;
}
