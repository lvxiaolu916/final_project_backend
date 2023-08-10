package com.training.backend.entity;

import lombok.Data;

import java.math.BigDecimal;


public class User {
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public BigDecimal getPrincipalHoldings() {
        return principalHoldings;
    }

    public void setPrincipalHoldings(BigDecimal principalHoldings) {
        this.principalHoldings = principalHoldings;
    }

    int userId;
    String userName;
    String userSex;
    BigDecimal principalHoldings;
}
