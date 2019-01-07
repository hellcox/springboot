package com.main.service.impl;

import com.main.dao.UserAccountMapper;
import com.main.dao.UserMapper;
import com.main.dao.dataobject.UserAccountDO;
import com.main.dao.dataobject.UserDO;
import com.main.error.EmError;
import com.main.error.FailException;
import com.main.service.TestService;
import com.main.service.model.UserModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
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
    private UserMapper userMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;

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
        userMapper.insertSelective(userDO1);
        System.out.println(userDO1);

        //int a = 1/0;
        if (true) {
            throw new FailException(EmError.FAIL);
        }

        UserDO userDO2 = new UserDO();
        userDO2.setName("2");
        userDO2.setNickName("2");
        userDO2.setSex(2);
        userMapper.insertSelective(userDO2);
        System.out.println(userDO2);
        return;
    }

    @Override
    public UserModel getUser(Integer id) throws FailException {
        UserDO userDO = userMapper.selectByPrimaryKey(id);
        if (userDO == null) {
            throw new FailException(EmError.FAIL, "用户不存在");
        }
        UserAccountDO userAccountDO = userAccountMapper.selectByUserId(userDO.getId());

        return this.convertFromDO(userDO, userAccountDO);
    }

    @Override
    public void sqlError() {
        userMapper.sqlError();
    }

    private UserModel convertFromDO(UserDO userDO, UserAccountDO userAccountDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        userModel.setGmtCreate(new DateTime(userDO.getGmtCreate()));
        if (userAccountDO != null) {
            userModel.setEmail(userAccountDO.getEmail());
            userModel.setMobile(userAccountDO.getMobile());
            userModel.setLoginName(userAccountDO.getLoginName());
            userModel.setPassword(userAccountDO.getPassword());
        }

        return userModel;
    }
}
