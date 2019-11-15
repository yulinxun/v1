package com.yu.v1productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.yu.api.IProductService;
import com.yu.commons.base.BaseServiceImpl;
import com.yu.commons.base.IBaseDao;
import com.yu.entity.TProduct;
import com.yu.entity.TProductDesc;
import com.yu.mapper.TProductDescMapper;
import com.yu.mapper.TProductMapper;
import com.yu.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yu
 * @date 2019/10/28 0028
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<TProduct> implements IProductService {
    @Autowired
    private TProductMapper productMapper;
    @Autowired
    private TProductDescMapper productDescMapper;
    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }

//    @Override
//    public PageInfo getPageInfo(int pageNum, int pageSize) {
//        PageHelper.startPage(pageNum,pageSize);
//        List<TProduct> list=productMapper.getList();
//        PageInfo<TProduct> pageInfo=new PageInfo<TProduct>(list,3);
//        return pageInfo;
//    }

    @Override
    @Transactional
    public long add(ProductVO vo) {
        //插入产品表
        productMapper.insertSelective(vo.getProduct());
        TProductDesc productDesc=new TProductDesc();
//        获取回填的主键
        productDesc.setProdcutId(vo.getProduct().getId());
        productDesc.setProductDesc(vo.getProductDesc());
        productDescMapper.insertSelective(productDesc);
//        插入产品描述表
        return vo.getProduct().getId();
    }

    @Override
    public ProductVO getVoById(Long id) {
        ProductVO vo=new ProductVO();
        TProduct tProduct = productMapper.selectByPrimaryKey(id);
        TProductDesc productDesc=productDescMapper.getDescByPId(id);
        vo.setProduct(tProduct);
        vo.setProductDesc(productDesc.getProductDesc());
        return vo;
    }

    @Override
    @Transactional
    public int update(ProductVO vo) {
        TProduct product = vo.getProduct();
        product.setUpdateTime(new Date());
        productMapper.updateByPrimaryKeySelective(product);
        TProductDesc productDesc=new TProductDesc();
        productDesc.setProductDesc(vo.getProductDesc());
        productDesc.setProdcutId(vo.getProduct().getId());
        productDescMapper.updateByPID(productDesc);
        return 1;
    }
}
