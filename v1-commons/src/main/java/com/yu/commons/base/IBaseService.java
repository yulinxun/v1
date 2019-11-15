package com.yu.commons.base;




import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yu
 * @date 2019/10/29 0029
 */
public interface IBaseService<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKeyWithBLOBs(T t);

    int updateByPrimaryKey(T t);

    List<T> getList();

    PageInfo getPageInfo(int pageNum, int pageSize);

    int del(Long id);

    int batchDel(Long[] ids);
}
