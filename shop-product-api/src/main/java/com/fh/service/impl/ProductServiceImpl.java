package com.fh.service.impl;

import com.fh.beans.po.ProductBean;
import com.fh.beans.vo.ProductBeanVo;
import com.fh.dao.ProductDao;
import com.fh.enumUtil.ResponseServer;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.pageutil.PageBean;

import java.util.List;

@Service("shopService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void queryShopListByTypeId(PageBean<ProductBeanVo> page, String typeId) {
        Long count = productDao.queryCount(typeId);
        page.setRecordsFiltered(count);
        page.setRecordTotal(count);
        List<ProductBeanVo> productBeanVoPageBean = productDao.queryShopListByTypeId(page, typeId);
        page.setData(productBeanVoPageBean);

    }

    @Override
    public void queryListBytypeIdAndbrandId(PageBean<ProductBeanVo> page, String typeId, String brandId, String value) {
        Long count = productDao.queryListCount(typeId,brandId,value);
        page.setRecordsFiltered(count);
        page.setRecordTotal(count);
        List<ProductBeanVo> productBeanVoPageBean = productDao.queryListBytypeIdAndbrandId(page, typeId,brandId,value);
        page.setData(productBeanVoPageBean);
    }

    @Override
    public ResponseServer getShopById(Integer shopId) {
        ProductBean productBean = productDao.selectById(shopId);
        return ResponseServer.success(productBean);
    }
}
