package com.yu.mapper;

import com.yu.commons.base.IBaseDao;
import com.yu.entity.TProductDesc;

public interface TProductDescMapper extends IBaseDao<TProductDesc> {

    TProductDesc getDescByPId(Long id);


    int updateByPID(TProductDesc productDesc);
}