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
@TableName("t_shop_brand")
public class BrandBean {
    @TableId(value = "bid",type = IdType.UUID)
    private String bid;

    private String bname;

    private Integer bstatus;

    private String pid;

    @DateTimeFormat(pattern = "yyydy-MM-dd")
    private Date pdate;//上市日期

    private String pdescribe;//描述

    @TableField("brandLogo")
    private String brandLogo;//图片

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }


}
