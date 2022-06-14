package com.ownerpro.web.shiro;

import com.ownerpro.web.dto.UserDTO;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import com.ownerpro.web.mapper.AdminMapper;
import com.ownerpro.web.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Object principal = principals.getPrimaryPrincipal();
        UserDTO userDTO = (UserDTO) principal;

        String username = userDTO.getUsername();
        int type = userDTO.getType();

        //注入角色与权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //管理员
        if(type == 1){
            info.addRole("admin");
            info.addRole("online");
        }

        //普通用户
        if(type == 0){
            info.addRole("user");
            info.addRole("online");
        }

        // get comment update insert and delete permission
        int selete = userDTO.getSelect_set();
        int update = userDTO.getUpdate_set();
        int delete = userDTO.getDelete_set();
        int insert = userDTO.getInsert_set();

        if(selete == 1){
            info.addRole("select");
        }
        if(update == 1){
            info.addRole("update");
        }
        if(delete == 1){
            info.addRole("delete");
        }
        if(insert == 1){
            info.addRole("insert");
        }
        System.out.println(info.getRoles());

        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //数据库匹配，认证
        String username = token.getUsername();
        String password = new String(token.getPassword());

        //管理员
        Admin admin = adminMapper.getAdminByUsername(username);
        if(admin != null && (admin.getPassword()+"").equals(password)){
            UserDTO userDTO = new UserDTO(admin);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDTO, token.getCredentials(), getName());

            return info;
        }

        //普通用户
        User user = userMapper.getUserByUsername(username);
        if(user != null && (user.getPassword()+"").equals(password)){
            UserDTO userDTO = new UserDTO(user);

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDTO, token.getCredentials(), getName());
            return info;
        }

        //认证失败
        throw new AuthenticationException();
    }
}
