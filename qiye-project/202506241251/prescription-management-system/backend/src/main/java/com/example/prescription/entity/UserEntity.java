package com.example.prescription.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 你的姓名
 * @since 2025-06-24
 */
@Getter
@Setter
@TableName("user")
@ApiModel(value = "UserEntity对象", description = "")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("real_name")
    private String realName;

    @TableField("role")
    private String role;

    @TableField("enabled")
    private Integer enabled;

    @TableField("created_time")
    private String createdTime;

    @TableField("last_login_time")
    private String lastLoginTime;

    @TableField("last_login_ip")
    private String lastLoginIp;
}
