package com.ownerpro.web.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("File entity")
public class Files implements Serializable
{
    @Id
    @ApiModelProperty("id")
    private Long file_id;

    @ApiModelProperty("文件路径")
    private String file_path;

    @ApiModelProperty("文件名称")
    private String file_name;

    @ApiModelProperty("文件后缀")
    private String file_suffix;

    public String getFileName() {
        return this.file_name;
    }

    public String getFilePath() {
        return this.file_path;
    }
}