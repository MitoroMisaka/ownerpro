package com.ownerpro.web.mapper;

import com.ownerpro.web.MyMapper;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * user  generated at 2019-11-30 18:52:33 by: undestiny
 */

@Repository
public interface UserMapper extends MyMapper<User> {

    @Update("UPDATE user SET password=#{password} WHERE username=#{username}")
    int updatePasswordByUsername(@Param("password")String password,@Param("username")String username);

    @Select("SELECT password FROM user WHERE username=#{username}")
    String getPasswordByUsername(@Param("username")String username);

    @Select("SELECT id,username,password,signDate,status FROM user WHERE username=#{username}")
    User getUserByUsername(@Param(("username")) String username);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User getUserById(Integer id);

    @Insert("INSERT INTO user(username, password, signDate, status) VALUES(#{username},#{password},#{signDate},0)")
    int insertUser(@Param("username")String username, @Param("password")String password
                , @Param("signDate")Timestamp signDate);

    @Update("UPDATE user SET status = #{status} WHERE username=#{username}")
    void updateUserStatus(@Param("status") Short status,@Param("username")String username);

    @Select("SELECT * FROM admin WHERE username=#{username}")
    Admin getAdminByUsername(@Param("username")String username);
}
