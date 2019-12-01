package com.fh.beans.vo;

import java.io.Serializable;

public class BrandBeanVo implements Serializable {

    private String bid;

    private String bname;

    private String brandLogo;//图片

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }
}
