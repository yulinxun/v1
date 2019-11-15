package com.yu.v1item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.IProductService;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.TProduct;
import com.yu.v1item.config.CommonConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import sun.nio.ch.ThreadPool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author yu
 * @date 2019/11/4 0004
 */
@Controller
@RequestMapping("item")
public class ItemController {
    @Autowired
    private Configuration configuration;
    @Reference
    private IProductService productService;
    @Autowired
    private ThreadPoolExecutor pool;
    @RequestMapping("createHtml/{id}")
    @ResponseBody
    public ResultBean  createHtml(@PathVariable("id")Long id) {
        TProduct product = productService.selectByPrimaryKey(id);
        Template template = null;
        try {
            template = configuration.getTemplate("item.ftl");
            Map<String,Object> data=new HashMap<>();
            data.put("product",product);
            String serverPath= ResourceUtils.getURL("classpath:static").getPath();
            Writer out=new FileWriter(serverPath+ File.separator+id+".html");
            template.process(data,out);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBean("500","读取模板失败");
        } catch (TemplateException e) {
            e.printStackTrace();
            return  new ResultBean("500","生成静态页面失败");
        }
        return  new ResultBean("200","生成静态页面成功");
    }
    @RequestMapping("batchCreateHtml")
    @ResponseBody
    public ResultBean  batchCreateHtml(@RequestParam("ids")Long[] ids) {

               Template template = null;
        List<Future<Long>> results=new ArrayList<>();
        for (Long id : ids) {
            results.add(pool.submit(new CreateHtmlTask(id)));
        }
        List<Long> errors=new ArrayList<>();
        for (Future<Long> future : results) {
            try {
                Long result=future.get();
                if (result!=0){
                    errors.add(result);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        for (Long error : errors) {
            System.out.println(error);
        }
        return  new ResultBean("200","生成静态页面成功");
    }

    private class CreateHtmlTask implements Callable<Long> {

        private Long productId;
        public CreateHtmlTask(Long productId){
            this.productId=productId;
        }
        @Override
        public Long call() {
            TProduct product = productService.selectByPrimaryKey(productId);
            Template template = null;
            try {
                template = configuration.getTemplate("item.ftl");
                Map<String,Object> data=new HashMap<>();
                data.put("product",product);
                String serverPath=ResourceUtils.getURL("classpath:static").getPath();
                Writer out=new FileWriter(serverPath+File.separator+productId+".html");
                template.process(data,out);
            } catch (IOException |TemplateException e) {
                e.printStackTrace();
                return productId;
            }
            return 0L;
        }
    }
}
