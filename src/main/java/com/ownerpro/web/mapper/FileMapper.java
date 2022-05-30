package com.ownerpro.web.mapper;

import com.ownerpro.web.entity.Files;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper {
    @Insert("INSERT INTO file (file_path, file_name, file_suffix) VALUES (#{file_path}, #{file_name}, #{file_suffix})")
    void insertFile(@Param("file_path")String file_path, @Param("file_name")String file_name, @Param("file_suffix")String file_suffix);

    @Select("SELECT * FROM file WHERE file_id = #{id}")
    Files selectFileById(@Param("id")Long id);
}
