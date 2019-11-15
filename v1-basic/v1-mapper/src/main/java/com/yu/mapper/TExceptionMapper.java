package com.yu.mapper;

import com.yu.entity.TException;

public interface TExceptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TException record);

    int insertSelective(TException record);

    TException selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TException record);

    int updateByPrimaryKey(TException record);
}