package com.main.service;

import com.main.dao.dataobject.UserDO;
import com.main.error.FailException;
import com.main.service.bo.UserBO;

/**
 * @author long
 * @date 2018/12/19 15:33
 */
public interface TestService {

    /**
     * 测试SERVICE
     *
     * @return
     */
    boolean test();

    /**
     * 事物处理
     */
    void transactional() throws FailException;

    /**
     * 查询用户
     * @return
     * @param id
     */
    UserBO getUser(Integer id) throws FailException;
}
