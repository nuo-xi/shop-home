package com.fh.beans.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
public class ProductBeanVo implements Serializable {

    private Integer id;//主键',

    private String brandId;// 类型ID',
    private String brandName;//类型名字

    private String shopBrandName;//品牌名

    private String name;//商品名称',

    private String subtitle;//宣传标题',

    private String mainImg;//主图片',

    private String subImgs;//'副图片',

    private String detail;//`商品描述',

    private Double price;//'价格',

    private Integer stock;//'库存',

    private Integer status;//'状态',

    private Date createTime;//'创建时间',

    private Date updateTime;//'修改时间',


}
