package com.main.dao;

import com.main.dao.dataobject.UserAccountDO;

public interface UserAccountMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserAccountDO record);

    int insertSelective(UserAccountDO record);

    UserAccountDO selectByPrimaryKey(Integer id);

    UserAccountDO selectByUserId(Integer id);

    int updateByPrimaryKeySelective(UserAccountDO record);

    int updateByPrimaryKey(UserAccountDO record);
}