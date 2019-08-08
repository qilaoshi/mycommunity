package com.example.community.community.model;

import lombok.Data;

@Data
public class Message {
    private String msg;
    private int toUser;
    private int fromUser;
    private String fromUsername;
    private String toUsername;
    private String time;
    private String fromHeadPicture;
    private String toHeadPicture;
}
