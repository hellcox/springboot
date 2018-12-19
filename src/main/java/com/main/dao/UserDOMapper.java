package com.main.dao;

import com.main.dao.model.UserDO;

public interface UserDOMapper {
    int insert(UserDO record);

    int insertSelective(UserDO record);
}