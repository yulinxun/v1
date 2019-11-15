package com.yu.api;

import com.github.pagehelper.PageInfo;
import com.yu.commons.base.IBaseService;
import com.yu.entity.TProduct;
import com.yu.vo.ProductVO;

/**
 * @author yu
 * @date 2019/10/28 0028
 */
public interface IProductService extends IBaseService<TProduct> {

    long add(ProductVO vo);

    ProductVO getVoById(Long id);

    int update(ProductVO vo);

//    PageInfo<TProduct> getPageInfo(int pageNum, int pageSize);
}
