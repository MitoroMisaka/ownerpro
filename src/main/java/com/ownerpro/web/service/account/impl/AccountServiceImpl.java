package com.ownerpro.web.service.account.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ownerpro.web.common.Page;
import com.ownerpro.web.common.Result;
import com.ownerpro.web.controller.account.PriorityRequest;
import com.ownerpro.web.dto.UserDTO;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import com.ownerpro.web.mapper.AdminMapper;
import com.ownerpro.web.mapper.UserMapper;
import com.ownerpro.web.service.BaseService;
import com.ownerpro.web.service.account.AccountService;
import com.ownerpro.web.common.EnumExceptionType;
import com.ownerpro.web.exception.RRException;
import com.ownerpro.web.util.PasswordUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Result changeRole(PriorityRequest request){
        //get id select update insert and delete
        Long id = request.getId();
        int select = request.getSelect_set();
        int update = request.getUpdate_set();
        int delete = request.getDelete_set();
        int insert = request.getInsert_set();
        userMapper.updateRole(id, select, update, delete, insert);
        return Result.success("修改成功");
    }

    @Override
    public Result getPriority(String username){
        //if username is in the user table return "用户" else return "管理员"
        User user = userMapper.getUserByUsername(username);
        if(user!=null){
            return Result.success("用户");
        }else{
            return Result.success("管理员");
        }
    }

    @Override
    public Page<UserDTO> getAllUsers(int pageNum, int pageSize, String orderBy){
        PageHelper.startPage(pageNum, pageSize, orderBy);
        //get all users and page it
        List<UserDTO> users = userMapper.getAllUsers();
        return new Page<>(new PageInfo<>(users));
    }
}
