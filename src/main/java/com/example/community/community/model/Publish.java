package com.example.community.community.model;

import lombok.Data;

@Data
public class Publish {
    private int id;
    private String title;
    private String content;
    private int creator;
    private Long gmtCreate;
    private Long gmtModified;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private User user;

}
