package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.beans.po.ProductBean;
import com.fh.beans.vo.ProductBeanVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.fh.pageutil.PageBean;

import java.util.List;

@Repository
@Mapper
public interface ProductDao extends BaseMapper<ProductBean> {
    List<ProductBeanVo> queryShopListByTypeId(@Param("page") PageBean<ProductBeanVo> page, @Param("typeId") String typeId);

    Long queryCount(@Param("typeId") String typeId);

    Long queryListCount(@Param("typeId") String typeId, @Param("brandId") String brandId,@Param("value")  String value);

    List<ProductBeanVo> queryListBytypeIdAndbrandId(@Param("page") PageBean<ProductBeanVo> page,@Param("typeId")  String typeId,@Param("brandId")  String brandId,@Param("value")  String value);
}
