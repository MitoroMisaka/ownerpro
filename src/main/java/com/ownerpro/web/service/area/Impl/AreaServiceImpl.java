package com.ownerpro.web.service.area.Impl;

import com.github.pagehelper.PageHelper;
import com.ownerpro.web.common.Result;
import com.ownerpro.web.controller.area.AreaResponse;
import com.ownerpro.web.entity.Area;
import com.ownerpro.web.mapper.AreaMapper;
import com.ownerpro.web.service.area.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaMapper areaMapper;

    @Override
    public List<AreaResponse> getAllAreas() {
        Long super_id = 0L;
        List<AreaResponse> areas = areaMapper.areaList(super_id);
        List<AreaResponse> childArea = new ArrayList<>();
        for(int i=0 ; i < areas.size();i++){
            AreaResponse currentArea = areas.get(i);
            AreaResponse area = new AreaResponse();
            Long id  = currentArea.getArea_id();
            System.out.println("id:"+id);
            area.setArea_id(id);
            area.setName(currentArea.getName());
            area.setSuper_id(currentArea.getSuper_id());
            area.setLevel(0L);
            area.setSubAreas(getChildAreas(id, 0L));
            childArea.add(area);
        }
        return childArea;
    }

    private List<AreaResponse> getChildAreas(Long id , Long level){
        List<AreaResponse> childAreas = areaMapper.areaList(id);
        for(AreaResponse area : childAreas){
            //if childArea is not null
            if(area.getSuper_id() != null){
                area.setLevel(level +1 );
                area.setSubAreas(getChildAreas(area.getArea_id(), level+1));
            }
        }
        return  childAreas;
    }

    @Override
    public Result addArea(String area, String parent_area) {
        Long super_id = 0L;
        if(parent_area != null){
            super_id = areaMapper.getAreaId(parent_area);
            if(super_id  == null)
                super_id = 0L;
        }
        areaMapper.insertArea(area, super_id);
        return Result.success("添加成功");
    }
}
