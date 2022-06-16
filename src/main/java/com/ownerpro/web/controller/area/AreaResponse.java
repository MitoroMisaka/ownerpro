package com.ownerpro.web.controller.area;

import com.ownerpro.web.entity.Area;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AreaResponse {
    @ApiModelProperty("id")
    private Long area_id = 0L;

    @ApiModelProperty("领域名")
    private String name;

    @ApiModelProperty("领域父id")
    private Long super_id;

    private Long level;

    private List<AreaResponse> subAreas;
}
