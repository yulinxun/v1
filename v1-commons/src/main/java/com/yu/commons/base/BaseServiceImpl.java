package com.yu.commons.base;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yu
 * @date 2019/10/29 0029
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {


    public abstract IBaseDao<T> getBaseDao();

    @Override
    public List<T> getList() {
        return getBaseDao().getList();
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return getBaseDao().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T t) {
        return getBaseDao().insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return getBaseDao().insertSelective(t);
    }

    @Override
    public T selectByPrimaryKey(Long id) {
        return getBaseDao().selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getBaseDao().updateByPrimaryKeySelective(t);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(T t) {
        return getBaseDao().updateByPrimaryKeyWithBLOBs(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return getBaseDao().updateByPrimaryKey(t);
    }

    @Override
    public int del(Long id) {
        return getBaseDao().del(id);
    }

    @Override
    public int batchDel(Long[] ids) {
        return getBaseDao().batchDel(ids);
    }

    @Override
    public PageInfo getPageInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<T> list=getBaseDao().getList();
        PageInfo<T> pageInfo=new PageInfo<T>(list,3);
        return pageInfo;
    }
}
