package com.fh.service.impl;

import com.alibaba.fastjson.JSON;
import com.fh.beans.vo.TypeBeanVo;
import com.fh.dao.TypeDao;
import com.fh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("typeService")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;


    @Override
    public List<Map<String, Object>> queryTypeList() {
        List<TypeBeanVo> typeList =  typeDao.queryTypeList();
        System.out.println(JSON.toJSONString(typeList));
        List<Map<String, Object>> typeZtree = getTypeZtree("0", typeList);
        System.out.println("service输出完毕");
        return typeZtree;
    }

    private List<Map<String, Object>> getTypeZtree(String s, List<TypeBeanVo> typeList) {
        List<Map<String, Object>> list = new ArrayList<>();
        typeList.forEach(typeBean -> {
            Map<String, Object> map =null;
            if(s.equals(typeBean.getPid())){
                map = new HashMap<>();
                map.put("id",typeBean.getId());
                map.put("pid",typeBean.getPid());
                map.put("name",typeBean.getTypeName());
                map.put("brandLogo",typeBean.getBrandLogo());
                map.put("children",getTypeZtree(typeBean.getId(),typeList));
            }
            if(map != null){
                list.add(map);
            }
        });
        System.out.println(JSON.toJSONString(list));

        return list;
    }
}
