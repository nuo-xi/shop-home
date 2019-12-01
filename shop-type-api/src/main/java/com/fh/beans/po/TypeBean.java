package com.fh.beans.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "t_shop_type")
public class TypeBean {

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    private String pid;

    @TableField(value = "typeName")
    private String typeName;

    @TableField(value = "isValid")
    private Integer isValid;


}
