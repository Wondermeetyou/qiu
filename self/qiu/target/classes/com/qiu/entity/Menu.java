package com.qiu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName menu
 */
@TableName(value ="menu")
@Data
public class Menu implements Serializable {
    /**
     * 菜单名
     */
    private String cateName;

    /**
     * 菜单id
     */
    private Integer cateId;

    /**
     * 角色id
     */
    private Integer roleId;

    private Integer parent_cate;

    private String path;

    private String icon;

}
