package com.yu.v1productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.yu.api.IProductTypeService;
import com.yu.commons.base.BaseServiceImpl;
import com.yu.commons.base.IBaseDao;
import com.yu.commons.base.IBaseService;
import com.yu.entity.TProductType;
import com.yu.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author yu
 * @date 2019/11/1 0001
 */
@Service
public  class ProductTypeServiceImpl extends BaseServiceImpl<TProductType> implements IProductTypeService {
    @Autowired
    private TProductTypeMapper productTypeMapper;
    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return productTypeMapper;
    }
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<TProductType> getList() {
        List<TProductType> list= (List<TProductType>) redisTemplate.opsForValue().get("productList");
        if (list==null){
            System.out.println("从数据库内获取");
            list = super.getList();
            redisTemplate.opsForValue().set("productList",list);
        }
        return list;
    }
}
