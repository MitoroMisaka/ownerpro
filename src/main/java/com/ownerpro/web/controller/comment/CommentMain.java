package com.ownerpro.web.controller.comment;

import com.ownerpro.web.dto.UserComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentMain {
    @Id
    @ApiModelProperty("id")
    private Long comment_id;

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("笔记id")
    private Long note_id;

    @ApiModelProperty("评论时间")
    private String comment_time;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("点赞数")
    private int likes;

    @ApiModelProperty("树形评论id")
    private Long super_id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("to_user")
    private String to_user;
}
