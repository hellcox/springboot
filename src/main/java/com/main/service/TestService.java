package com.main.service;

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
    void transactional();
}
