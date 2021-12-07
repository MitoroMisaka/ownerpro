package com.phoenix.logistics.mapper;

import com.phoenix.logistics.MyMapper;
import com.phoenix.logistics.controller.response.BriefUserOrder;
import com.phoenix.logistics.dto.TmpUserOrder;
import com.phoenix.logistics.entity.AdminOrder;
import com.phoenix.logistics.entity.User;
import com.phoenix.logistics.entity.UserOrder;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderMapper extends MyMapper<UserOrder>{
    @Insert("INSERT INTO userOrder(senderUsername,receiverUsername,goodsId,status,statusUpdateTime,originLocation,destinationLocation,commitTime,transportTime) VALUES (#{senderUsername},#{receiverUsername},#{goodsId},#{status},#{statusUpdateTime},#{originLocation},#{destinationLocation},#{commitTime},#{transportTime})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void submitUserOrder(UserOrder userOrder);

    @Update("UPDATE userOrder SET status=#{status},statusUpdateTime=#{statusUpdateTime} WHERE id=#{id}")
    void changStatus(@Param("status")Integer status,@Param("statusUpdateTime")String statusUpdateTime,@Param("id")Long id);

    @Select("SELECT * FROM userOrder WHERE id=#{id}")
    UserOrder getUserOrderById(@Param("id")Long id);

    @Update("UPDATE userOrder SET adminOrderId=#{adminOrderId} WHERE id=#{id}")
    void beDealed(@Param("adminOrderId")Long adminOrderId,@Param("id")Long id);

    @Select("SELECT * FROM userOrder WHERE status=#{status} AND username=#{username}")
    List<UserOrder> getTransportingUserOrderByStatusAndUsername(@Param("status")Integer status,@Param("username")String username);

    @Select("SELECT id,senderUsername,receiverUsername,status FROM userOrder WHERE senderUsername=#{username} OR receiverUsername=#{username}")
    List<TmpUserOrder> getBriefUserOrderList(String username);

    @Select("SELECT id,senderUsername,receiverUsername,status FROM userOrder WHERE senderUsername=#{username}")
    List<TmpUserOrder> getBriefUserSendOrderList(String username);

    @Select("SELECT id,senderUsername,receiverUsername,status FROM userOrder WHERE receiverUsername=#{username}")
    List<TmpUserOrder> getBriefReceiveUserOrderList(String username);

    @Select("SELECT * FROM userOrder WHERE status=#{status}")
    List<AdminOrder> getUserOrderByStatus(@Param("status") Integer status);


}
