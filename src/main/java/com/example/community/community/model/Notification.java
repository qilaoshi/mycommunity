package com.example.community.community.model;

import java.util.List;

public class Notification {
    private int id;
    private int notifier;
    private int receiver;
    private int outerId;
    private int type;
    private Long gmtCreate;
    private int status;
    private User user;
    private Publish publish;

    public Publish getPublish() {
        return publish;
    }

    public void setPublish(Publish publish) {
        this.publish = publish;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNotifier() {
        return notifier;
    }

    public void setNotifier(int notifier) {
        this.notifier = notifier;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getOuterId() {
        return outerId;
    }

    public void setOuterId(int outerId) {
        this.outerId = outerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
