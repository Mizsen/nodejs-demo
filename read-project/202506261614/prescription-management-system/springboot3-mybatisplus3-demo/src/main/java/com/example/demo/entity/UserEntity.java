package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@TableName("user")  // 必须使用MyBatis Plus注解
@Getter
@Setter
// @ApiModel(value = "UserEntity对象", description = "")
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
