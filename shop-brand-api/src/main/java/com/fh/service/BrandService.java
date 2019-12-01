package com.fh.service;

import com.fh.beans.vo.BrandBeanVo;

import java.util.List;

public interface BrandService {
    List<BrandBeanVo> queryBrandList(String typeId);
}
