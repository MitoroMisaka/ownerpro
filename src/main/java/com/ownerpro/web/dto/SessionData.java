package com.ownerpro.web.dto;

import com.ownerpro.web.common.CommonErrorCode;
import com.ownerpro.web.entity.User;
import com.ownerpro.web.util.AssertUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * session缓存实体
 * @author yan on 2020-02-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("SessionData 会话实体")
public class SessionData implements Serializable {

    /**
     * {@link com.ownerpro.web.entity.User}
     */
    @Id
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("类型")
    private String type;

    public SessionData(User user){
        AssertUtil.notNull(user, CommonErrorCode.USER_NOT_EXIST);
//        if(user == null){
//            create_time = TimeUtil.getCurrentTimestamp();
//            nickname = "小镇用户";
//            gender = 0;
//            campus = CampusEnum.ZHONG_BEI.getName();
//            signature = "ta很懒，还没有签名哦~";
//            schoolAuth = 0;
//            return;
//        }

//        id = user.getId();
//        create_time = user.getCreate_time();
//        nickname = user.getNickname();
//        gender = user.getGender();
//        portrait = user.getPortrait();
    }
}
