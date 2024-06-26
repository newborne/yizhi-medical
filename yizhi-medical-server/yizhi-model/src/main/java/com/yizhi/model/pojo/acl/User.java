package com.yizhi.model.pojo.acl;

import com.yizhi.model.pojo.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户")
@TableName("acl_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "用户签名")
    @TableField("token")
    private String token;
}



