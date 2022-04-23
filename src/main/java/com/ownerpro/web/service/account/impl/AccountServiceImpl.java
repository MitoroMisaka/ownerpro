package com.ownerpro.web.service.account.impl;

import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import com.ownerpro.web.mapper.AdminMapper;
import com.ownerpro.web.mapper.UserMapper;
import com.ownerpro.web.service.BaseService;
import com.ownerpro.web.service.account.AccountService;
import com.ownerpro.web.common.EnumExceptionType;
import com.ownerpro.web.exception.RRException;
import com.ownerpro.web.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl extends BaseService implements AccountService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void signUp(String username,String password, String name) {

        User user = User.builder().username(username).build();

        user.setPassword(PasswordUtil.convert(password));

        userMapper.insertUser(user.getUsername(),user.getPassword(), name);

    }

    @Override
    public void changePassword(String username,String password){
        String originalPassword = userMapper.getPasswordByUsername(username);
        if(originalPassword.equals(PasswordUtil.convert(password))){
            throw new RRException(EnumExceptionType.PASSWORD_SAME);
        }
        userMapper.updatePasswordByUsername(PasswordUtil.convert(password),username);
    }


    @Override
    public void checkUsername(String username){
        User user = userMapper.getUserByUsername(username);
        if(user!=null){
            throw new RRException(EnumExceptionType.PASSWORD_SAME);
        }
    }

    @Override
    public User getUser(String username){
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void checkPassword(String username,String password){
        if(userMapper.getPasswordByUsername(username).equals(password)){
            throw new RRException(EnumExceptionType.USER_ALREADY_EXIST);
        }
    }

    @Override
    public Admin getAdmin(String username){
        return userMapper.getAdminByUsername(username);
    }
}
