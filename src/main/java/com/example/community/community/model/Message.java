package com.example.community.community.model;

import lombok.Data;

@Data
public class Message {
    private String msg;
    private String toUser;
    private String fromUser;
}
