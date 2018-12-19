package com.main.service.impl;

import com.main.dao.UserDOMapper;
import com.main.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author long
 * @date 2018/12/19 15:34
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserDOMapper userDOMapper;
    @Override
    public boolean test() {
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void transactional() {
        return;
    }
}
