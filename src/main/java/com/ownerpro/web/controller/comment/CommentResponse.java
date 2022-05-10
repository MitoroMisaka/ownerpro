package com.ownerpro.web.controller.comment;

import com.ownerpro.web.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("CommentResponse")
public class CommentResponse {
    @ApiModelProperty("主评论")
    private Comment comment;

    @ApiModelProperty("子评论")
    private List<Comment> sub_comment;
}
