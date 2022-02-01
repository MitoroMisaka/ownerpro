package com.ownerpro.web.service.admin;

import com.ownerpro.web.common.Page;
import com.ownerpro.web.controller.response.BriefAdminOrder;
import com.ownerpro.web.controller.response.OrderDetailResponse;
import com.ownerpro.web.controller.response.TmpAdminOrder;
import com.ownerpro.web.entity.AdminOrder;

import java.util.List;

public interface AdminOrderService {
    void dealAdminOrder(Long id,Long carId,Long driverId,String adminUsername);

    AdminOrder getAdminOrderById(Long id);

    OrderDetailResponse getOrderDetailResponse(Long adminOrderId);

    Page<TmpAdminOrder> getBriefAdminOrderListByStatus(int pageNum, int pageSize, int status);

    List<BriefAdminOrder> getAdminMessageList();

    Page<TmpAdminOrder> searchAdminOrder(int pageNum,int pageSize,Integer id);
}
