package com.ownerpro.web.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ownerpro.web.common.EnumExceptionType;
import com.ownerpro.web.common.Page;
import com.ownerpro.web.entity.Driver;
import com.ownerpro.web.exception.RRException;
import com.ownerpro.web.service.admin.DriverService;
import com.ownerpro.web.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    com.ownerpro.web.mapper.DriverMapper DriverMapper;

    @Autowired
    RedisUtil redisUtil;


    @Override
    public Page<Driver> getAllDrivers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Driver> DriverList = DriverMapper.getAllDrivers();
        return new Page<>(new PageInfo<>(DriverList));
    }

    @Override
    public void addDriver(Integer num){
        for(int i = 0;i<num;i++){
            DriverMapper.insertDriver(0);
        }
    }

    @Override
    public void deleteDriver(Long id){
        Driver Driver = DriverMapper.getDriverById(id);
        if(Driver.getStatus()==1) throw new RRException(EnumExceptionType.DRIVER_BEING_USED);
        DriverMapper.deleteDriver(id);
    }

    @Override
    public Page<Driver> getAllFreeDrivers(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Driver> driverList = DriverMapper.getAllFreeDrivers(0);
        return new Page<>(new PageInfo<>(driverList));
    }

    @Override
    public List<Driver> getAllFreeDrivers(){
        return DriverMapper.getAllFreeDrivers(0);
    }
}
