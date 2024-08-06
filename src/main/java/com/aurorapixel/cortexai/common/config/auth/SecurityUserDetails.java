package com.aurorapixel.cortexai.common.config.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 认证系统用户
 */
@Getter
public class SecurityUserDetails extends User {
    /**
     * 初始化
     * @param username 用户姓名
     * @param password 密码
     * @param authorities 权限
     */
    public SecurityUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityUserDetails(Long userId,String username, String password, String name, Integer sex, String email, String phone, Integer status, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
        this.account = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 状态:[0:未激活 1:初始 2:激活 3:禁用]
     */
    private Integer status;


}
