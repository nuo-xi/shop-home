package com.fh.beans;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartBean implements Serializable {

    private Integer shopId;//id

    private String shopName;//商品名

    private String shopImg;//图片

    private BigDecimal shopPrice;//价格

    private Integer count;//条数

    private Boolean isCheck;//选中状态

    private BigDecimal subTotal;// 小计

    private Integer  isStatus ;//1有货,0无货

    private String detail;//商品描述

}
