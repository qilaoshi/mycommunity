package com.example.community.community.model;

import lombok.Data;

@Data
public class NotifiWithUserWithPublish {
    private int userId;
    private String username;
    private int id;
    private String title;
    private String content;
    private int nId;
    private int notifier;
    private int status;

}
