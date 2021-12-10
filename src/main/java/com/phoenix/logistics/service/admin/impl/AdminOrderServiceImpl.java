package com.phoenix.logistics.service.admin.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phoenix.logistics.common.Page;
import com.phoenix.logistics.controller.response.BriefAdminOrder;
import com.phoenix.logistics.controller.response.OrderDetailResponse;
import com.phoenix.logistics.dto.TmpAdminOrder;
import com.phoenix.logistics.entity.Admin;
import com.phoenix.logistics.entity.AdminOrder;
import com.phoenix.logistics.entity.Goods;
import com.phoenix.logistics.entity.UserOrder;
import com.phoenix.logistics.mapper.*;
import com.phoenix.logistics.service.admin.AdminOrderService;
import com.phoenix.logistics.util.DisTranUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    AdminOrderMapper adminOrderMapper;

    @Autowired
    UserOrderMapper userOrderMapper;

    @Autowired
    CarMapper carMapper;

    @Autowired
    DriverMapper driverMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public void dealAdminOrder(Long id,Long carId,Long driverId,String adminUsername){
        //Integer tranTime = DisTranUtil.tranTime(submitUserOrderRequest.getOriginLng(),submitUserOrderRequest.getOriginLat(),submitUserOrderRequest.getDestinationLng(),submitUserOrderRequest.getDestinationLat());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(date);
        Integer transportTime = adminOrderMapper.getTransportTimeById(id);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, transportTime);
        Date arriveDate = c.getTime();
        String arriveTime = simpleDateFormat.format(arriveDate);
        adminOrderMapper.DealAdminOrder(carId,driverId,adminUsername,1,currentTime,arriveTime,currentTime,id);
        adminOrderMapper.readAdminOrder(0,id);
        carMapper.allocateCar(1,carId);
        driverMapper.allocateDriver(1,driverId);
        AdminOrder adminOrder = adminOrderMapper.getAdminOrderById(id);
        userOrderMapper.changStatus(adminOrder.getStatus(),adminOrder.getStatusUpdateTime(),adminOrder.getUserOrderId());
        userOrderMapper.readUserOrder(0,adminOrder.getUserOrderId());
    }


    @Override
    public AdminOrder getAdminOrderById(Long id){
        return adminOrderMapper.getAdminOrderById(id);
    }

    @Override
    public OrderDetailResponse getOrderDetailResponse(Long adminOrderId){
        AdminOrder adminOrder = adminOrderMapper.getAdminOrderById(adminOrderId);
        UserOrder userOrder = userOrderMapper.getUserOrderById(adminOrder.getUserOrderId());
        Goods goods = goodsMapper.getGoodsById(userOrder.getGoodsId());
        adminOrderMapper.readAdminOrder(1,adminOrder.getId());
        userOrderMapper.readUserOrder(1,userOrder.getId());
        return new OrderDetailResponse(goods.getId(),goods.getName(),goods.getType(),goods.getVolume(),goods.getWeight(),goods.getValue(),userOrder.getSenderUsername(),userOrder.getReceiverUsername(),userOrder.getStatus(),userOrder.getStatusUpdateTime(),userOrder.getOriginLocation(),userOrder.getDestinationLocation(),userOrder.getCommitTime(),userOrder.getReceiveTime(),adminOrder.getCarId(),adminOrder.getDriverId(),adminOrder.getAdminUsername(),adminOrder.getTransportTime(),adminOrder.getSendTime(),adminOrder.getArriveTime());
    }

    @Override
    public Page<BriefAdminOrder> getBriefAdminOrderList(int pageNum, int pageSize){
        updateTransportingAdminOrderStatus();
        PageHelper.startPage(pageNum, pageSize,"statusUpdateTime desc");
       List<TmpAdminOrder> tmpAdminOrderArrayList = adminOrderMapper.getBriefAdminOrderList();
       ArrayList<BriefAdminOrder> briefAdminOrderArrayList = new ArrayList<>();
       for(TmpAdminOrder tmpAdminOrder:tmpAdminOrderArrayList){
           AdminOrder adminOrder = adminOrderMapper.getAdminOrderById(tmpAdminOrder.getId());
           UserOrder userOrder = userOrderMapper.getUserOrderById(tmpAdminOrder.getUserOrderId());
           briefAdminOrderArrayList.add(new BriefAdminOrder(adminOrder.getId(),userOrder.getSenderUsername(),userOrder.getReceiverUsername(),adminOrder.getStatus(),adminOrder.getStatusUpdateTime()));
       }
       return new Page<BriefAdminOrder>(new PageInfo<>(briefAdminOrderArrayList));
    }

    private void updateTransportingAdminOrderStatus(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(date);
        List<AdminOrder> adminOrderList = adminOrderMapper.getAdminOrderByStatus(1);
        for(AdminOrder adminOrder:adminOrderList){
            if(adminOrder.getArriveTime().compareTo(currentTime)<0){
                adminOrderMapper.changeStatus(2,adminOrder.getArriveTime(),adminOrder.getId());
                adminOrderMapper.readAdminOrder(0,adminOrder.getId());
                carMapper.allocateCar(0,adminOrder.getCarId());
                driverMapper.allocateDriver(0,adminOrder.getDriverId());
                if(userOrderMapper.getUserOrderById(adminOrder.getUserOrderId()).getStatus()==1)
                    userOrderMapper.changStatus(2,adminOrder.getArriveTime(),adminOrder.getUserOrderId());
                    userOrderMapper.readUserOrder(0,adminOrder.getUserOrderId());
            }
        }
    }

    @Override
    public Page<BriefAdminOrder> getBriefAdminOrderListByStatus(int pageNum, int pageSize,int status){
        if(status==0)PageHelper.startPage(pageNum, pageSize,"statusUpdateTime desc");
        else PageHelper.startPage(pageNum, pageSize,"statusUpdateTime asc");
        List<AdminOrder> AdminOrderArrayList = adminOrderMapper.getAdminOrderByStatus(status);
        ArrayList<BriefAdminOrder> briefAdminOrderArrayList = new ArrayList<>();
        for(AdminOrder adminOrder:AdminOrderArrayList){
            UserOrder userOrder = userOrderMapper.getUserOrderById(adminOrder.getUserOrderId());
            briefAdminOrderArrayList.add(new BriefAdminOrder(adminOrder.getId(),userOrder.getSenderUsername(),userOrder.getReceiverUsername(),adminOrder.getStatus(),adminOrder.getStatusUpdateTime()));
        }

        return new Page<BriefAdminOrder>(new PageInfo<>(briefAdminOrderArrayList));
    }


    @Override
    public List<BriefAdminOrder> getAdminMessageList(){
        List<TmpAdminOrder> tmpAdminOrderList = adminOrderMapper.getAdminMessage(0);
        ArrayList<BriefAdminOrder> briefAdminOrderArrayList = new ArrayList<>();
        for(TmpAdminOrder tmpAdminOrder:tmpAdminOrderList){
            UserOrder userOrder = userOrderMapper.getUserOrderById(tmpAdminOrder.getUserOrderId());
            briefAdminOrderArrayList.add(new BriefAdminOrder(tmpAdminOrder.getId(),userOrder.getSenderUsername(),userOrder.getReceiverUsername(),tmpAdminOrder.getStatus(),tmpAdminOrder.getStatusUpdateTime()));
        }
        return briefAdminOrderArrayList;
    }
}
