package com.fh.service;

import com.fh.beans.vo.ProductBeanVo;
import com.fh.enumUtil.ResponseServer;
import com.fh.pageutil.PageBean;

public interface ProductService {

    void queryShopListByTypeId(PageBean<ProductBeanVo> page,String typeId);

    void queryListBytypeIdAndbrandId(PageBean<ProductBeanVo> page, String typeId, String brandId,  String value);

    ResponseServer getShopById(Integer shopId);
}
