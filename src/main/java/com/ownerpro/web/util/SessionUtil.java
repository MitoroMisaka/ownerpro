package com.ownerpro.web.util;

import com.ownerpro.web.common.CommonConstants;
import com.ownerpro.web.dto.SessionData;
import com.ownerpro.web.entity.User;
import com.ownerpro.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Repository
public class SessionUtil {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    public String getUserName(){
        return Optional
                .ofNullable(getSessionData())
                .orElse(new SessionData())
                .getName();
    }

    public SessionData getSessionData(){
        String key = request.getHeader(CommonConstants.SESSION);
        if(key == null)return null;

        SessionData sessionData = null;
        try {
            sessionData = (SessionData) redisUtil.get(key);
        }catch (Exception e){
            return getSessionDataFromDB(key);

        }
        if(sessionData != null)return sessionData;
        return getSessionDataFromDB(key);
    }

    private SessionData getSessionDataFromDB(String key) {
        SessionData sessionData;
        User user = userMapper.selectOne(User.builder().sessionId(key).build());
        if(user != null){
            sessionData = new SessionData(user);
            redisUtil.set(key,sessionData);
            return sessionData;
        }else{
            redisUtil.set(key,null,3600);
            return null;
        }
    }
}
