package com.main.service;

import com.main.error.FailException;
import com.main.service.model.UserModel;

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
     * @throws FailException
     */
    void transactional() throws FailException;

    /**
     * 通过用户ID查询用户
     * @param id
     * @return
     * @throws FailException
     */
    UserModel getUser(Integer id) throws FailException;

    /**
     * 错误的sql
     */
    void sqlError();

    /**
     * 插入大量数据
     */
    void insertMore();
}
