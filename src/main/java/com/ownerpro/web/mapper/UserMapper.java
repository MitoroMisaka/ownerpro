package com.ownerpro.web.mapper;

import com.ownerpro.web.MyMapper;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * user  generated at 2019-11-30 18:52:33 by: undestiny
 * user  updated at 2022-4-23 10:44:33 by: rai
 */

@Repository
public interface UserMapper extends MyMapper<User> {


    @Update("UPDATE user SET password=#{password} WHERE username=#{username}")
    int updatePasswordByUsername(@Param("password")String password,@Param("username")String username);

    @Select("SELECT password FROM user WHERE username=#{username}")
    String getPasswordByUsername(@Param("username")String username);

    @Select("SELECT id,name,username,password FROM user WHERE username=#{username}")
    User getUserByUsername(String username);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User getUserById(Integer id);

    @Insert("INSERT INTO user(username,password,name) VALUES(#{username},#{password},#{name})")
    int insertUser(@Param("username")String username,@Param("password")String password, @Param("name")String name);

    @Update("UPDATE user SET name=#{name} WHERE username=#{username}")
    void updateUserName(@Param("name")String name,@Param("username")String username);

    @Select("SELECT * FROM admin WHERE username=#{username}")
    Admin getAdminByUsername(@Param("username")String username);
}
