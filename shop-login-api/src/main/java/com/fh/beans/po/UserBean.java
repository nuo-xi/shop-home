package com.fh.beans.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@TableName(value = "t_shop_user")
public class UserBean  implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;//id

    private String account;//账号

    private String pwd;//密码

    private String nickname;//昵称

    private String emal;//邮箱

    private String  telnum;//电话号码

    private String cartid;//购物车id


}
