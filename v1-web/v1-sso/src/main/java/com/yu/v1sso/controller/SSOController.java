package com.yu.v1sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.IUserService;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.TUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author yu
 * @date 2019/11/13 0013
 */
@Controller
@RequestMapping("sso")
public class SSOController {
    @Reference
    private IUserService userService;

    @PostMapping("checkLogin")
    @ResponseBody
    public ResultBean checkLogin(TUser user){
        return null;
    }

    @PostMapping("checkLogin4PC")
    public String checkLogin4PC(TUser user, HttpServletRequest request, HttpServletResponse response){
        ResultBean resultBean = userService.checkLogin(user);
        if ("200".equals(resultBean.getStatusCode())){
//            request.getSession().setAttribute("user",user);session方式存储登录凭证
            String uuid = (String) resultBean.getData();
            Cookie cookie=new Cookie("user_token",uuid);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return "redirect:http://localhost:9093/";
        }
        return "index";
    }
    @GetMapping("checkIsLogin")
    @ResponseBody
    @CrossOrigin(origins = "*",allowCredentials ="true")
    public ResultBean checkIsLogin(@CookieValue(name = "user_token",required = false)String uuid){
        if (uuid!=null){
            ResultBean resultBean=userService.checkIsLogin(uuid);
            return resultBean;
        }
        return new ResultBean("404",null);
    }
    @GetMapping("logout")
    @ResponseBody
    public ResultBean logout(@CookieValue(name = "user_token",required = false)String token,HttpServletResponse response){
        if(token!=null){
            Cookie cookie=new Cookie("user_token",token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return new ResultBean("200",true);
    }
}
