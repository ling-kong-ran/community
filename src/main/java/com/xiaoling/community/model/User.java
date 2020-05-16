package com.xiaoling.community.model;

public class User {
    private Integer id;
    private String name;
    private String accountid;
    private String token;
    private Long gmtcreate;
    private Long gmtmodified;

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

    public String getAccountID() {
        return accountid;
    }

    public void setAccountID(String accountID) {
        this.accountid = accountID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmtCreate() {
        return gmtcreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtcreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtmodified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtmodified = gmtModified;
    }
}
