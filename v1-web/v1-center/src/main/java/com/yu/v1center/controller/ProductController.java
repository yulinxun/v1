package com.yu.v1center.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yu.api.IProductService;
import com.yu.commons.constant.MQConstant;
import com.yu.entity.TProduct;
import com.yu.v1center.config.TopicSender;
import com.yu.vo.ProductVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yu
 * @date 2019/10/29 0029
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProductService productService;
    @Autowired
    private RabbitTemplate template;
    @RequestMapping("get/{id}")
    @ResponseBody
    public TProduct getById(@PathVariable("id") Long id){
        return productService.selectByPrimaryKey(id);
    }
    @RequestMapping("list")
    public String getList(ModelMap map){
        List<TProduct> list=productService.getList();
        map.addAttribute("list",list);
        return "product/list";
    }
    @RequestMapping("page/{pageNum}/{pageSize}")
    public String getPage(ModelMap map,@PathVariable("pageNum")int pageNum,@PathVariable("pageSize")int pageSize){

        PageInfo<TProduct> pageInfo=productService.getPageInfo(pageNum,pageSize);
        map.addAttribute("page",pageInfo);
        return "product/list";
    }
    @RequestMapping("add")
    public String add(ProductVO vo){
        Long newId=productService.add(vo);
        template.convertAndSend(MQConstant.V1_CENTER_EXCHANGE,"product.add",newId);
        return "redirect:/product/page/1/1";
    }
    @RequestMapping("del/{id}")
    public String del(@PathVariable("id")Long id){
        Long newId=(long)productService.del(id);
        template.convertAndSend(MQConstant.V1_CENTER_EXCHANGE,"product.del",newId);
        return "redirect:/product/page/1/1";
    }
    @RequestMapping("toUpdate/{id}")
    @ResponseBody
    public ProductVO toUpdate(@PathVariable("id")Long id){
        ProductVO vo = productService.getVoById(id);
        return vo;
    }
    @RequestMapping("update")
    public String update(ProductVO vo){
        productService.update(vo);
        return "redirect:/product/page/1/1";
    }
    @RequestMapping("batchDel/{arr}")
    @ResponseBody
    public String batchDel(@PathVariable("arr")Long[] ids){
        productService.batchDel(ids);
        return "xxx";
    }

}
