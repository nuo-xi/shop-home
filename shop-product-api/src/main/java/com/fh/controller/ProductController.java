package com.fh.controller;

import com.fh.beans.vo.ProductBeanVo;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fh.pageutil.PageBean;

@RestController
@RequestMapping("/shop")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 点击二级类型节点
     * 通过类型id查询负荷条件的数据
     *
     * @param page   分页
     * @param typeId 类型id
     * @return
     */
    @RequestMapping("/queryShopListByTypeId")
    @CrossOrigin(maxAge = 3000, origins = "http://localhost:8080")
    public PageBean<ProductBeanVo> queryShopListByTypeId(PageBean<ProductBeanVo> page,
                                                         String typeId) {
        System.out.println("product接口进来的参数是" + typeId);
        productService.queryShopListByTypeId(page, typeId);
        return page;
    }

    /**
     * 点击三级节点
     * 查询符合条件的数据集合
     *
     * @param typeId  类型id
     * @param brandId 品牌id
     * @return
     */
    @RequestMapping("/queryListBytypeIdAndbrandId")
    @CrossOrigin(maxAge = 3000, origins = "http://localhost:8080")
    public PageBean<ProductBeanVo> queryListBytypeIdAndbrandId(String typeId,
                                                               String brandId,
                                                               String value,
                                                               PageBean<ProductBeanVo> page) {
        productService.queryListBytypeIdAndbrandId(page, typeId, brandId, value);
        return page;
    }
}
