package com.main.service.impl;

import com.main.dao.UserDOMapper;
import com.main.dao.model.UserDO;
import com.main.error.EmMainError;
import com.main.error.FailException;
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
    @Transactional(rollbackFor = Exception.class)
    public void transactional() throws FailException {
        UserDO userDO1 = new UserDO();
        userDO1.setName("1");
        userDO1.setNickName("1");
        userDO1.setSex(1);
        userDOMapper.insertSelective(userDO1);
        System.out.println(userDO1);

        //int a = 1/0;
        if(true){
            throw new FailException(EmMainError.FAIL);
        }

        UserDO userDO2 = new UserDO();
        userDO2.setName("2");
        userDO2.setNickName("2");
        userDO2.setSex(2);
        userDOMapper.insertSelective(userDO2);
        System.out.println(userDO2);
        return;
    }

    @Override
    public UserDO getUser(Integer id) {
        return userDOMapper.selectByPrimaryKey(id);
    }
}
