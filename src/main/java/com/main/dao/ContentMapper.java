package com.main.dao;

import com.main.dao.dataobject.ContentDO;

public interface ContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContentDO record);

    int insertSelective(ContentDO record);

    ContentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContentDO record);

    int updateByPrimaryKeyWithBLOBs(ContentDO record);

    int updateByPrimaryKey(ContentDO record);
}