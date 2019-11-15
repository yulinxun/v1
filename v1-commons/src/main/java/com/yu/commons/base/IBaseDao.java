package com.yu.commons.base;



import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yu
 * @date 2019/10/29 0029
 */
public interface IBaseDao<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKeyWithBLOBs(T t);

    int updateByPrimaryKey(T t);

    <T> List<T> getList();

    int del(Long id);

    int batchDel(Long[] ids);

    PageInfo getPageInfo(int pageNum, int pageSize);
}
