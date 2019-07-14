package com.example.community.community.Service;

import com.example.community.community.model.Focus;


public interface FocusService {
    void insert(Focus focus);
    Focus selectAll(Focus focus);
    void delete(Focus focus);
}
