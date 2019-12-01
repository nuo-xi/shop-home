package com.fh.beans.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TypeBeanVo implements Serializable {

    private String id;

    private String pid;

    private String typeName;

    private Integer isValid;

    private Boolean checked;

    private String brandLogo;
}
