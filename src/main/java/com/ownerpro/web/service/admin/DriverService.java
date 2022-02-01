package com.ownerpro.web.service.admin;

import com.ownerpro.web.common.Page;
import com.ownerpro.web.entity.Driver;

import java.util.List;

public interface DriverService {
    Page<Driver> getAllDrivers(int pageNum, int pageSize);

    void addDriver(Integer num);

    void deleteDriver(Long id);

    Page<Driver> getAllFreeDrivers(int pageNum,int pageSize);

    List<Driver> getAllFreeDrivers();
}
