package com.fh.service.impl;


import com.fh.beans.vo.BrandBeanVo;
import com.fh.dao.BrandDao;
import com.fh.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("brandService")
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public List<BrandBeanVo> queryBrandList(String typeId) {
        return brandDao.queryBrandList(typeId);
    }
}
