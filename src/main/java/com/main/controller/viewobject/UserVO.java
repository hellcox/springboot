package com.main.controller.viewobject;

import java.math.BigDecimal;

/**
 * @author long
 * @date 2018/12/21 13:36
 */
public class UserVO {

    private Integer id;

    private String name;

    private String nickName;

    private Integer sex;

    private BigDecimal money;

    private String gmtCreate;

    private String mobile;

    private String email;

    private String loginName;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", money=" + money +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", loginName='" + loginName + '\'' +
                '}';
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
