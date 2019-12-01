package com.fh.beans.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@TableName(value = "t_shop_product")
public class ProductBean {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;//主键',

    @TableField("shopBrand")
    private String shopBrand;//品牌

    @TableField("brandId")
    private String brandId;// 类型ID',

    private String name;//商品名称',

    private String subtitle;//宣传标题',

    @TableField("mainImg")
    private String mainImg;//主图片',

    @TableField("subImgs")
    private String subImgs;//'副图片',

    private String detail;//`商品描述',

    private Double price;//'价格',

    private Integer stock;//'库存',

    private Integer status;//'状态',

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("createTime")
    private Date createTime;//'创建时间',

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("updateTime")
    private Date updateTime;//'修改时间',


}
