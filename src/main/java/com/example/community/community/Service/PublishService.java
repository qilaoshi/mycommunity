package com.example.community.community.Service;

import com.example.community.community.model.Publish;

import java.util.List;

public interface PublishService {
    void insert(Publish publish);
    List<Publish> select();
}
