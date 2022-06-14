package com.ownerpro.web.dto;

import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {

    private Long id;
    private String username;
    private int type;//0为普通用户，1为管理员

    private int select;

    private int update;

    private int delete;

    private int insert;

    public UserDTO(User user){
        id = user.getId();
        username = user.getUsername();
        type = 0;
    }

    public UserDTO(Admin admin){
        id = admin.getId();
        username = admin.getUsername();
        type = 1;
    }

}
