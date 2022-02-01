package com.ownerpro.web.service.admin;

import com.ownerpro.web.common.Page;
import com.ownerpro.web.entity.Car;

import java.util.List;


public interface CarService {

    Page<Car> getAllCars(int pageNum, int pageSize);

    void addCar(Integer num);

    void deleteCar(Long id);

    Page<Car> getAllFreeCars(int pageNum,int pageSize);

    List<Car> getAllFreeCars();
}
