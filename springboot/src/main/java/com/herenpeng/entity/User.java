package com.herenpeng.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author herenpeng
 * @since 2021-03-07 20:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("auth_user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "enabled")
    private Boolean enabled;

    @TableField(value = "locked")
    private Boolean locked;

    @TableField(value = "account_expire")
    private Boolean accountExpire;

    @TableField(value = "password_expire")
    private Boolean passwordExpire;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Integer createUserId;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private Integer updateUserId;

    @TableLogic
    private Boolean deleted;
}
