package com.ownerpro.web.entity;


import com.ownerpro.web.controller.comment.CommentMain;
import com.ownerpro.web.dto.UserComment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("comment entity")
public class Comment implements Serializable
{
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
    private UserComment name;

    @ApiModelProperty("to_user")
    private UserComment to_user;

    //Constructor function with CommentMain UserComment Usercomment
    public Comment(CommentMain commentMain, UserComment name, UserComment to_user)
    {
        this.comment_id = commentMain.getComment_id();
        this.id = commentMain.getId();
        this.note_id = commentMain.getNote_id();
        this.comment_time = commentMain.getComment_time();
        this.content = commentMain.getContent();
        this.likes = commentMain.getLikes();
        this.super_id = commentMain.getSuper_id();
        this.name = name;
        this.to_user = to_user;
    }
}