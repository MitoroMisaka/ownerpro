package com.ownerpro.web.service.team.Impl;

import com.ownerpro.web.entity.Team;
import com.ownerpro.web.mapper.*;
import com.ownerpro.web.service.BaseService;
import com.ownerpro.web.service.account.AccountService;
import com.ownerpro.web.common.EnumExceptionType;
import com.ownerpro.web.controller.request.UpdateUserMessageRequest;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import com.ownerpro.web.exception.RRException;
import com.ownerpro.web.mapper.AdminMapper;
import com.ownerpro.web.mapper.UserMapper;
import com.ownerpro.web.service.BaseService;
import com.ownerpro.web.service.account.AccountService;
import com.ownerpro.web.service.team.TeamService;
import com.ownerpro.web.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamServiceImpl extends BaseService implements TeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public void signUp(String name, Short age, String hobby, String contact, String introduction){
        teamMapper.insertTeam(name, age, hobby, contact, introduction);
    }

    @Override
    public void changeAge(Short age, String name){
        teamMapper.updateAgeByName(name, age);
    }

    @Override
    public void changeHobby(String hobby, String name){
        teamMapper.updateHobbyByName(hobby, name);
    }

    @Override
    public void changeContact(String contact, String name){
        teamMapper.updateContactByName(contact, name);
    }

    @Override
    public void changeIntroduction(String introduction, String name ){
        teamMapper.updateIntroductionByName(introduction, name);
    }

    @Override
    public Team getTeam(String name){
        return teamMapper.getTeamByName(name);
    }
}
