package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.FocusMsgService;
import com.example.community.community.mapper.FocusMsgMapper;
import com.example.community.community.model.FocusMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FocusMsgServiceImpl implements FocusMsgService {
    @Autowired
    private FocusMsgMapper focusMsgMapper;
    @Override
    public void insert(FocusMsg focusMsg) {
        focusMsgMapper.insert(focusMsg);
    }
}
