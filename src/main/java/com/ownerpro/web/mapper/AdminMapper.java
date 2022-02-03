package com.ownerpro.web.mapper;

import com.ownerpro.web.MyMapper;
import com.ownerpro.web.entity.Admin;
import com.ownerpro.web.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * user  generated at 2019-11-30 18:52:33 by: undestiny
 */

@Repository
public interface AdminMapper extends MyMapper<User> {

    @Select("SELECT * FROM admin WHERE userName=#{userName}")
    Admin getAdminByUsername(String username);

}
