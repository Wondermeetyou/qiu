package com.qiu.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 姓名
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电话
     */
    private String phone;

    /**
     * 角色 0超级管理员 1管理员 2普通账号
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 是否有效 y有效 其他无效
     */
    @TableField("isValid")
    private String isvalid;

    /**
     * token
     */
    private String token;


    private String image;


}
