package com.o4care.nurse.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginBean {

    /**
     * changePasswordRequried : 1
     * token : 0c25f518a4124cb99b8d9c87e3e58157
     * roleId : 0
     * expireIn : 0
     * userName :
     * userTypes : null
     */

    private int changePasswordRequried;
    private String token;
    private int roleId;
    private int expireIn;
    private String userName;
    private Object userTypes;

    public void setChangePasswordRequried(int changePasswordRequried) {
        this.changePasswordRequried = changePasswordRequried;
    }
    public int getChangePasswordRequried() {
        return changePasswordRequried;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public int getRoleId() {
        return roleId;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
    public int getExpireIn() {
        return expireIn;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserTypes(Object userTypes) {
        this.userTypes = userTypes;
    }
    public Object getUserTypes() {
        return userTypes;
    }
}
