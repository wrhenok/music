package com.kaifamiao.music.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 用户电话
     */
    private String telephone;

    /**
     * 用户性别(0:男，1:女)
     */
    private Integer gender;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户类型(0:管理员，1:普通用户)
     */
    private Integer usertype;

    private static final long serialVersionUID = 1L;
}