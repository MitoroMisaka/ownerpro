package com.ownerpro.web.mapper;

import com.ownerpro.web.entity.Goods;
import org.apache.ibatis.annotations.*;
import com.ownerpro.web.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsMapper extends MyMapper<Goods>{
    @Insert("INSERT INTO goods(name,type,volume,weight,value) VALUES (#{name},#{type},#{volume},#{weight},#{value})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void addGoods(Goods good);

    @Select("SELECT * FROM goods WHERE id=#{id}")
    Goods getGoodsById(Long id);

}
