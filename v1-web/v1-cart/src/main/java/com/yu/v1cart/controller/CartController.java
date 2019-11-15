package com.yu.v1cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.ICartService;
import com.yu.commons.pojo.ResultBean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author yu
 * @date 2019/11/14 0014
 */
@Controller
@RequestMapping("cart")
public class CartController {

    @Reference
    private ICartService cartService;
    @GetMapping("add/{productId}/{count}")
    @ResponseBody
    public ResultBean add(@PathVariable("productId") Long productId,
                          @PathVariable("count") int count, HttpServletResponse response,
                          @CookieValue(name = "cart_token",required = false)String token){
        if (token==null){
            token= UUID.randomUUID().toString();
        }
        ResultBean resultBean = cartService.add(token, productId, count);
        reflushCookie(response, token);
        return new ResultBean("200",token);
    }


    @GetMapping("query")
    @ResponseBody
    public ResultBean query(HttpServletResponse response,@CookieValue(name = "cart_token",required = false)String token){
        if(token!=null){
            ResultBean resultBean = cartService.query(token);
            reflushCookie(response, token);
            return resultBean;
        }
        return new ResultBean("404",null);
    }
    @GetMapping("update/{productId}/{count}")
    @ResponseBody
    public ResultBean update(HttpServletResponse response,@PathVariable("productId") Long productId,
                             @PathVariable("count") int count,
                             @CookieValue(name = "cart_token",required = false)String token){
        if(token!=null){
            ResultBean resultBean = cartService.update(token,productId,count);
            reflushCookie(response, token);
            return resultBean;
        }
        return new ResultBean("404",null);
    }
    @GetMapping("del/{productId}")
    @ResponseBody
    public ResultBean del(HttpServletResponse response,@PathVariable("productId") Long productId,
                          @CookieValue(name = "cart_token",required = false)String token){
        if(token!=null){
            ResultBean resultBean = cartService.del(token,productId);
            reflushCookie(response, token);
            return resultBean;
        }
        return new ResultBean("404",null);
    }

    private void reflushCookie(HttpServletResponse response, @CookieValue(name = "cart_token", required = false) String token) {
        Cookie cookie=new Cookie("cart_token",token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(15*60*60*24);
        response.addCookie(cookie);
    }
}
