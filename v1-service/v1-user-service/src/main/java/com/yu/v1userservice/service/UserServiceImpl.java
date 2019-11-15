package com.yu.v1userservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yu.api.IUserService;
import com.yu.commons.base.BaseServiceImpl;
import com.yu.commons.base.IBaseDao;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.TUser;
import com.yu.mapper.user.TUserMapper;

import com.yu.v1userservice.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yu
 * @date 2019/11/11 0011
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<TUser> implements IUserService {
    @Autowired
    private TUserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public ResultBean checkUserNameIsExists(String username) {
        if (userMapper.checkUserNameIsExists(username)>0){
            return new ResultBean("500","用户名已被占用");
        }
        return new ResultBean("200","用户名可用");
    }

    @Override
    public ResultBean checkPhoneIsExists(String phone) {
        return null;
    }

    @Override
    public ResultBean checkEmailIsExists(String email) {

        return null;
    }

    @Override
    public ResultBean checkLogin(TUser user) {
        TUser currentUser=userMapper.selectUserByIdentification(user.getUsername());
        if (currentUser!=null){
            if (currentUser.getPassword().equals(user.getPassword())){
                JwtUtils jwtUtils=new JwtUtils();
                jwtUtils.setSecretKey("java");
                jwtUtils.setTtl(30*60*1000);
                String token=jwtUtils.createJwtToken(currentUser.getId().toString(),currentUser.getUsername());
//                String uuid= UUID.randomUUID().toString();
//                redisTemplate.opsForValue().set("user:token:"+uuid,user.getUsername(),30, TimeUnit.MINUTES);
                return new ResultBean("200",token);
            }
        }
        return new ResultBean("404",null);
    }

    @Override
    public ResultBean checkIsLogin(String uuid) {
//        StringBuffer key=new StringBuffer("user:token:").append(uuid);
//        String username = (String) redisTemplate.opsForValue().get(key.toString());
//        if(username!=null){
//            return new ResultBean("200",username);
//        }
        try {
            JwtUtils jwtUtils=new JwtUtils();
            jwtUtils.setSecretKey("java");
            jwtUtils.parseJwtToken(uuid);
        }catch (RuntimeException e){
            return new ResultBean("404",null);
        }
        return new ResultBean("200",uuid);
    }

    @Override
    public IBaseDao<TUser> getBaseDao() {
        return userMapper;
    }
}
