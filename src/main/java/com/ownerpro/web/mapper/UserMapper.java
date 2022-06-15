package com.ownerpro.web.mapper;

import com.ownerpro.web.MyMapper;
import com.ownerpro.web.dto.UserDTO;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("SELECT * FROM user WHERE username=#{username}")
    User getUserByUsername(String username);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User getUserById(Integer id);

    @Select("SELECT id FROM user WHERE username=#{username}")
    Integer getIdByUsername(@Param("username")String username);

    @Insert("INSERT INTO user(username,password,name) VALUES(#{username},#{password},#{name})")
    int insertUser(@Param("username")String username,@Param("password")String password, @Param("name")String name);

    @Update("UPDATE user SET name=#{name} WHERE username=#{username}")
    void updateUserName(@Param("name")String name,@Param("username")String username);

    @Select("SELECT * FROM admin WHERE username=#{username}")
    Admin getAdminByUsername(@Param("username")String username);

    //set select update insert and delete
    @Update("UPDATE user SET select_set=#{select},update_set=#{update},delete_set=#{delete},insert_set=#{insert} WHERE id = #{id}")
    void updateRole(@Param("id")Long id, @Param("select")Integer select, @Param("update")Integer update, @Param("delete")Integer delete, @Param("insert")Integer insert);

    @Select("SELECT * FROM user")
    List<UserDTO> getAllUsers();

    //delete User by id
    @Update("DELETE FROM user WHERE id = #{id}")
    void deleteUser(@Param("id")Long id);
}
