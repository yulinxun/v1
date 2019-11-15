package com.yu.v1register.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.IUserService;
import com.yu.commons.pojo.ResultBean;
import com.yu.commons.util.CodeUtils;
import com.yu.entity.TProduct;
import com.yu.entity.TUser;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yu
 * @date 2019/11/11 0011
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Reference
    private IUserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("checkUserNameIsExists/{username}")
    public ResultBean checkUserNameIsExists(@PathVariable("username")String username){
        return userService.checkUserNameIsExists(username);
    }
    @GetMapping("checkPhoneIsExists/{username}")
    public ResultBean checkPhoneIsExists(@PathVariable("phone")String phone){
        return userService.checkPhoneIsExists(phone);
    }
    @GetMapping("checkEmailIsExists/{username}")
    public ResultBean checkEmailIsExists(@PathVariable("email")String email){
        return userService.checkEmailIsExists(email);
    }
    @PostMapping("generateCode/{identification}")
    @ResponseBody
    public ResultBean generateCode(@PathVariable("identification")String identification){
        String code = CodeUtils.generateCode(6);
        redisTemplate.opsForValue().set(identification,code,3, TimeUnit.MINUTES);
        Map<String,String> params=new HashMap<>();
        params.put("code",code);
        params.put("phone",identification);
        rabbitTemplate.convertAndSend("v1-message-exchange","message.code",params);
//        Map<String,String> params2=new HashMap<>();
//        params2.put("to","1061745620@qq.com");
//        params2.put("username","lin");
//        rabbitTemplate.convertAndSend("v1-mail-exchange","mail.activate",params2);
       return new ResultBean("200","成功发送验证码");
    }

    /**
     * 适合处理异步请求
     * @param user
     * @return
     */
    @PostMapping("register")
    public ResultBean register(TUser user){
        userService.insert(user);
        return null;
    }
    /**
     * 适合处理同步请求
     * @param user
     * @return
     */
    @PostMapping("register4PC")
    public String register4PC(TUser user){
        userService.insert(user);
        return "index";
    }
    @GetMapping("activating")
    public String activating(String token){
        return null;
    }
}
