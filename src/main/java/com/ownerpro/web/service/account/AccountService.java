package com.ownerpro.web.service.account;

import com.ownerpro.web.controller.request.UpdateUserMessageRequest;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;

public interface AccountService {

    //注册
    void signUp(String username,String password, String name);

    void changePassword(String username,String password);

    void checkPassword(String username,String password);

    void checkUsername(String username);

    User getUser(String username);

    Admin getAdmin(String username);
}
