package com.ownerpro.web.service;

import com.ownerpro.web.util.RedisUtil;
import com.ownerpro.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    public RedisUtil redisUtil;

    @Autowired
    public UserMapper userMapper;

}
