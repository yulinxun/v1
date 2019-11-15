package com.yu.mapper;

import com.yu.commons.base.IBaseDao;
import com.yu.entity.TProduct;
import com.yu.vo.ProductVO;

public interface TProductMapper extends IBaseDao<TProduct> {

    ProductVO getVoById(Long id);
}