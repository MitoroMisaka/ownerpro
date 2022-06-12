package com.ownerpro.web.controller.comment;

import com.ownerpro.web.dto.UserComment;
import com.ownerpro.web.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("CommentResponse")
public class CommentResponse {
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

    @ApiModelProperty("子评论")
    private List<Comment> sub_comment;

    //constructor with Comment and List<Comment>
    public CommentResponse(Comment comment, List<Comment> sub_comment)
    {
        this.comment_id = comment.getComment_id();
        this.id = comment.getId();
        this.note_id = comment.getNote_id();
        this.comment_time = comment.getComment_time();
        this.content = comment.getContent();
        this.likes = comment.getLikes();
        this.super_id = comment.getSuper_id();
        this.name = comment.getName();
        this.to_user = comment.getTo_user();
        this.sub_comment = sub_comment;
    }
}
