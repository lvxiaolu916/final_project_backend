package com.training.backend.entity;

import lombok.Data;

@Data
public class User {
    int userId;
    String userName;
    String userSex;
    int principalHoldings;
}
