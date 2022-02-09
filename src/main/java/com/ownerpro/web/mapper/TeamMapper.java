package com.ownerpro.web.mapper;

import com.ownerpro.web.MyMapper;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.Team;
import com.ownerpro.web.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMapper extends MyMapper<Team>{
    @Update("UPDATE team SET age = #{age} WHERE name = #{name}")
    int updateAgeByName(@Param("name")String name,
                        @Param("age")Short age);

    @Update("UPDATE team SET hobby = #{hobby} WHERE name = #{name}")
    int updateHobbyByName(@Param("name")String name,
                        @Param("hobby")String hobby);

    @Update("UPDATE team SET contact = #{contact} WHERE name = #{name}")
    int updateContactByName(@Param("name")String name,
                        @Param("contact")String contact);

    @Update("UPDATE team SET introduction = #{introduction} WHERE name = #{name}")
    int updateIntroductionByName(@Param("name")String name,
                        @Param("introduction")String introduction);

    @Select("SELECT * FROM team WHERE name=#{name}")
    Team getTeamByName(@Param("name")String name);

    @Insert("INSERT INTO team VALUES(null,#{name},#{age},#{contact},#{introduction})")
    int insertTeam(@Param("name")String name,
                   @Param("age")Short age,
                   @Param("hobby")String hobby,
                   @Param("contact")String contact,
                   @Param("introduction")String introduction);
}
