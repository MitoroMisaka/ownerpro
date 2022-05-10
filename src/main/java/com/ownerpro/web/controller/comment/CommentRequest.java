package com.ownerpro.web.controller.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("CommentRequest")
public class CommentRequest {
    @ApiModelProperty("comment")
    private String content;
    @ApiModelProperty("article_id")
    private Long article_id;
    @ApiModelProperty("super_id")
    private Long super_id;
}
