package com.ownerpro.web.mapper;

import com.ownerpro.web.MyMapper;
import com.ownerpro.web.controller.area.AreaResponse;
import com.ownerpro.web.entity.Area;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaMapper extends MyMapper<Area> {
    //get the areas where super_id  = 0
    @Select("select * from area where super_id = #{super_id}")
    List<AreaResponse> areaList(@Param("super_id")Long super_id);

    @Select("select area_id from area where name = #{name}")
    Long getAreaId(@Param("name")String name);

    @Insert("insert into area(name, super_id) values(#{name}, #{super_id})")
    void insertArea(@Param("name")String name, @Param("super_id")Long super_id);
}
