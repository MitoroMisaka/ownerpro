package com.ownerpro.web.service.team;

import com.ownerpro.web.controller.request.UpdateUserMessageRequest;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.Team;
import com.ownerpro.web.entity.User;

public interface TeamService {
    void signUp(String name, Short age, String hobby, String contact, String introduction);

    void changeAge(Short age, String name);

    void changeHobby(String hobby, String name);

    void changeContact(String contact, String name);

    void changeIntroduction(String introduction, String name );

    Team getTeam(String name);
}
