package com.yu.v1index.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.IProductTypeService;
import com.yu.api.ISearchService;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.TProduct;
import com.yu.entity.TProductType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author yu
 * @date 2019/11/1 0001
 */
@Controller
@RequestMapping("index")
public class IndexController {
    @Reference
    private IProductTypeService productTypeService;
    @Reference
    private ISearchService searchService;
    @RequestMapping("show")
    public String show(Model model) {
            List<TProductType> list = productTypeService.getList();
        model.addAttribute("list", list);
        return "index";
    }
    @RequestMapping("show2")
    public String show2(Model model)  {
        return "Editor";
    }
//    @RequestMapping("search")
//    public String serach(String keywords, Model model, HttpServletResponse response){
//        return "redirect:http://localhost:9092/search/queryByKeyword4PC?keywords="+keywords;
//    }
    @RequestMapping("from")
    @ResponseBody
    public ResultBean from(@RequestHeader(name = "Referer",required = false)String from){
        return new ResultBean("200",from);
    }
}
