package com.ownerpro.web.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ownerpro.web.common.EnumExceptionType;
import com.ownerpro.web.common.Page;
import com.ownerpro.web.entity.Car;
import com.ownerpro.web.exception.RRException;
import com.ownerpro.web.mapper.CarMapper;
import com.ownerpro.web.service.admin.CarService;
import com.ownerpro.web.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {


    @Autowired
    CarMapper carMapper;

    @Autowired
    RedisUtil redisUtil;


    @Override
    public Page<Car> getAllCars(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Car> carList = carMapper.getAllCars();
        return new Page<>(new PageInfo<>(carList));
    }

    @Override
    public void addCar(Integer num){
        for(int i = 0;i<num;i++){
            carMapper.insertCar(0);
        }
    }

    @Override
    public void deleteCar(Long id){
        Car car = carMapper.getCarById(id);
        if(car.getStatus()==1) throw new RRException(EnumExceptionType.CAR_BEING_USED);
        carMapper.deleteCar(id);
    }

    @Override
    public Page<Car> getAllFreeCars(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Car> carList = carMapper.getAllFreeCars(0);
        return new Page<>(new PageInfo<>(carList));
    }

    @Override
    public List<Car> getAllFreeCars(){
        return carMapper.getAllFreeCars(0);
    }
}
