package com.yu.v1search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.ISearchService;
import com.yu.commons.pojo.PageResultBean;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.TProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yu
 * @date 2019/11/2 0002
 */
@Controller
@RequestMapping("search")
@Slf4j
public class SearchController {
    @Reference
    private ISearchService searchService;
    @RequestMapping("synById/{id}")
    @ResponseBody
    public ResultBean synById(@PathVariable("id")Long id){
        ResultBean resultBean = searchService.synById(id);
        return new ResultBean<>("200","数据同步成功");
    }
    @RequestMapping("synAllData")
    @ResponseBody
    public ResultBean sysAll(){
        ResultBean resultBean = searchService.synAllData();
        return new ResultBean<>("200","数据同步成功");
    }
    @RequestMapping("queryByKeyword4PC/{pageIndex}/{pageSize}")
    public String getList(String keywords, Model model,
                          @PathVariable("pageIndex")int pageIndex,
                          @PathVariable("pageSize")int pageSize){
        ResultBean resultBean = searchService.quertByKeywords(keywords,pageIndex,pageSize);
        PageResultBean<TProduct> pageResultBean = (PageResultBean<TProduct>) resultBean.getData();
        model.addAttribute("page",pageResultBean);
        return "list";
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
