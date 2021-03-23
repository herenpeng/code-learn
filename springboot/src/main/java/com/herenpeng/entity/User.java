package com.herenpeng.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author herenpeng
 * @since 2021-03-07 20:07
 */
@ApiModel(value = "用户信息实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("auth_user")
public class User implements Serializable {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名称")
    @TableField(value = "username")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @TableField(value = "password")
    private String password;

    @ApiModelProperty(value = "用户账号是否启用")
    @TableField(value = "enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "用户账号是否锁定")
    @TableField(value = "locked")
    private Boolean locked;

    @ApiModelProperty(value = "用户账号是否过期")
    @TableField(value = "account_expire")
    private Boolean accountExpire;

    @ApiModelProperty(value = "用户密码是否过期")
    @TableField(value = "password_expire")
    private Boolean passwordExpire;

    @ApiModelProperty(value = "数据库数据创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "数据库数据插入用户主键")
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Integer createUserId;

    @ApiModelProperty(value = "数据库数据更新时间")
    @TableField(value = "update_time")
    private Date updateTime;

    @ApiModelProperty(value = "数据库数据更新用户主键")
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private Integer updateUserId;

    @ApiModelProperty(value = "数据库数据标识逻辑删除字段")
    @TableLogic
    private Boolean deleted;
}
