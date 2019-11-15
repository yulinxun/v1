package com.yu.api;

import com.yu.commons.base.IBaseService;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.TProduct;

/**
 * @author yu
 * @date 2019/11/2 0002
 */
public interface ISearchService{
    public ResultBean synAllData();
    public ResultBean synById(Long id);
    public ResultBean delById(Long id);
    public ResultBean quertByKeywords(String keywords,int pageIndex,int pageSize);
    public ResultBean quertByKeywords(String keywords);
}
