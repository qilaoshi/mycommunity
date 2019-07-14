package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.FocusService;
import com.example.community.community.mapper.FocusMapper;
import com.example.community.community.model.Focus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FocusServiceImpl implements FocusService {
    @Autowired
    private FocusMapper focusMapper;

    @Override
    public void insert(Focus focus) {
        focusMapper.insert(focus);
    }

    @Override
    public Focus selectAll(Focus focus) {
        return focusMapper.selectAll(focus);
    }

    @Override
    public void delete(Focus focus) {
        focusMapper.delete(focus);
    }
}
