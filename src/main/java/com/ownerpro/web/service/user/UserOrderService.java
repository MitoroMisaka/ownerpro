package com.ownerpro.web.service.user;

import com.ownerpro.web.common.Page;
import com.ownerpro.web.controller.request.SubmitUserOrderRequest;
import com.ownerpro.web.controller.response.BriefUserOrder;
import com.ownerpro.web.controller.response.TmpUserOrder;
import com.ownerpro.web.entity.UserOrder;

import java.util.HashMap;
import java.util.List;

public interface UserOrderService {
    HashMap<String,Long> submitUserOrder(String username, SubmitUserOrderRequest submitUserOrderRequest);

    int receiveGoods(Long id);

    UserOrder getUserOrderById(Long id);

    Page<TmpUserOrder> getBriefUserOrderListByCondition(int pageNum, int pageSize, String username, int sendOrReceive, int status);

    List<BriefUserOrder> getUserMessageList(String username);

    Page<TmpUserOrder> searchUserOrder(int pageNum, int pageSize,Integer id,String username,int sendOrReceive);
}
