package com.phoenix.logistics.controller.user;

import com.phoenix.logistics.common.Result;
import com.phoenix.logistics.controller.request.SubmitUserOrderRequest;
import com.phoenix.logistics.entity.AdminOrder;
import com.phoenix.logistics.entity.UserOrder;
import com.phoenix.logistics.service.user.UserOrderService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

@RestController
@Api("用户订单控制器")
@RequestMapping("/user_order")
@Validated
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @RequiresRoles("user")
    @PostMapping("/submit")
    @ApiOperation("提交用户订单")
    public Result SubmitUserOrder(@NotNull @Valid @RequestBody SubmitUserOrderRequest submitUserOrderRequest){
        HashMap<String,Long> data = userOrderService.submitUserOrder(submitUserOrderRequest);
        return Result.success("提交成功",data);
    }

    @GetMapping("/status")
    @ApiOperation("修改用户订单状态（请一定在调用管理员的处理订单接口后立即调用）")
    @ApiImplicitParam(name = "id",value = "刚刚发货的用户订单的id",paramType = "query",dataType = "Long")
    public Result changeUserOrderStatus(@NotNull@RequestParam(value = "id")Long id){
        UserOrder userOrder = userOrderService.getAdminOrderById(id);
        return Result.success("修改成功！！",userOrder);
    }

    @RequiresRoles("user")
    @PostMapping("/receive")
    @ApiOperation("用户确认收货")
    @ApiImplicitParam(name = "id",value = "处理的用户订单的id",paramType = "query",dataType = "Long")
    public Result receiveGoods(@NotNull@RequestParam(value = "id")Long id){
        if(userOrderService.receiveGoods(id)==0) return Result.fail("货物未处理或未送达");
        return Result.success("收货成功");
    }
}
