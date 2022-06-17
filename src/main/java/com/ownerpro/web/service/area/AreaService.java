package com.ownerpro.web.service.area;

import com.ownerpro.web.common.Result;
import com.ownerpro.web.controller.area.AreaResponse;

import java.util.List;

public interface AreaService {
    List<AreaResponse> getAllAreas();

    Result addArea(String area, String parent_area);

}
