package com.yu.v1cartservice.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.yu.api.ICartService;
import com.yu.api.IProductService;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.CartItem;
import com.yu.entity.CartItemVO;
import com.yu.entity.TProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yu
 * @date 2019/11/14 0014
 */
@Service
public class CartServiceImpl implements ICartService {
    @Resource(name = "myRedisTemplate")
    private RedisTemplate redisTemplate;
    @Reference
    private IProductService productService;

    @Override
    public ResultBean add(String token, Long productId, int count) {
        StringBuffer key = new StringBuffer("cart:token:").append(token);
        Map<Long, CartItem> cart = redisTemplate.opsForHash().entries(key.toString());
        if (cart != null) {
            if (redisTemplate.opsForHash().hasKey(key.toString(), productId)) {
                CartItem cartItem = (CartItem) redisTemplate.opsForHash().get(key.toString(), productId);
                cartItem.setCount(cartItem.getCount() + count);
                cartItem.setUpdateTime(new Date());
                redisTemplate.opsForHash().put(key.toString(), productId, cartItem);
                redisTemplate.expire(key.toString(), 15, TimeUnit.DAYS);
                return new ResultBean("2000", cartItem);
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setCount(count);
        cartItem.setUpdateTime(new Date());
        redisTemplate.opsForHash().put(key.toString(), productId, cartItem);
        redisTemplate.expire(key.toString(), 15, TimeUnit.DAYS);
        return new ResultBean("200", cartItem);
    }

    @Override
    public ResultBean del(String token, Long productId) {
        StringBuffer key = new StringBuffer("cart:token:").append(token);
        redisTemplate.opsForHash().delete(key.toString(), productId);
        return new ResultBean("200",true);
    }

    @Override
    public ResultBean update(String token, Long productId, int count) {
        StringBuffer key = new StringBuffer("cart:token:").append(token);
        CartItem cartItem = (CartItem) redisTemplate.opsForHash().get(key.toString(), productId);
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setUpdateTime(new Date());
            redisTemplate.opsForHash().put(key.toString(), productId, cartItem);
            return new ResultBean("200",cartItem);
        }
            return new ResultBean("404",null);
    }

    @Override
    public ResultBean query(String token) {
        StringBuffer key = new StringBuffer("cart:token:").append(token);
        Map<Long, CartItem> cart = redisTemplate.opsForHash().entries(key.toString());
        Map<Integer, TProduct> map = new HashMap<>();
        for (Long productId : cart.keySet()) {
            TProduct product = productService.selectByPrimaryKey(productId);
            int count = cart.get(productId).getCount();//cartItem.getCount
            map.put(count,product);
        }
        return new ResultBean("200",map);
    }
}
