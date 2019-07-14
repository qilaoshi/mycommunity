package com.example.community.community.mapper;

import com.example.community.community.model.Focus;

import java.util.List;

public interface FocusMapper {
    void insert(Focus focus);
    Focus selectAll(Focus focus);
    void delete(Focus focus);
}
