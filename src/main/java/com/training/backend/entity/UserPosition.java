package com.training.backend.entity;

import lombok.Data;

@Data
public class UserPosition {
    private int userId;
    private int stockId;
    private int volume;
    private double principalInput;
}
