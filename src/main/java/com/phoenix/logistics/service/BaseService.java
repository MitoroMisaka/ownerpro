package com.phoenix.logistics.service;

import com.phoenix.logistics.mapper.CompanyMapper;
import com.phoenix.logistics.mapper.InviteMapper;
import com.phoenix.logistics.mapper.UserMapper;
import com.phoenix.logistics.mapper.User_roleMapper;
import com.phoenix.logistics.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    public RedisUtil redisUtil;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public InviteMapper inviteMapper;

    @Autowired
    public User_roleMapper user_roleMapper;

    @Autowired
    public CompanyMapper companyMapper;

}
