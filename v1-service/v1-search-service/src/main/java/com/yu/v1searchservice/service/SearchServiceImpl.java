package com.yu.v1searchservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yu.api.ISearchService;
import com.yu.commons.pojo.PageResultBean;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.TProduct;
import com.yu.mapper.TProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yu
 * @date 2019/11/2 0002
 */
@Service
@Slf4j
public class SearchServiceImpl implements ISearchService {
    @Autowired
    private TProductMapper productMapper;
    @Autowired
    private SolrClient solrClient;

    @Override
    public ResultBean synAllData() {
        List<TProduct> list=productMapper.getList();
        for (TProduct product : list) {
            SolrInputDocument document=new SolrInputDocument();
            document.setField("id", product.getId());
            document.setField("product_name",product.getName());
            document.setField("product_price",product.getPrice());
            document.setField("product_sale_point",product.getSalePoint());
            document.setField("product_images",product.getImages());
            try {
                solrClient.add(document);
            } catch (SolrServerException |IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
                return new ResultBean<>("500","同步数据失败");
            }
        }
        try {
            solrClient.commit();
        } catch (SolrServerException |IOException e) {
            e.printStackTrace();
            return new ResultBean<>("500","同步数据失败");
        }
        return new ResultBean<>("200","同步数据成功");
    }

    @Override
    public ResultBean synById(Long id) {
        TProduct product = productMapper.selectByPrimaryKey(id);
        SolrInputDocument document=new SolrInputDocument();
        document.setField("id", product.getId());
        document.setField("product_name",product.getName());
        document.setField("product_price",product.getPrice());
        document.setField("product_sale_point",product.getSalePoint());
        document.setField("product_images",product.getImages());
        try {
            solrClient.add(document);
            solrClient.commit();
        }catch (SolrServerException |IOException e) {
            e.printStackTrace();
            return new ResultBean<>("500","同步数据失败");
        }
        return new ResultBean<>("200","同步数据成功");
    }

    @Override
    public ResultBean delById(Long id) {
        try {
            solrClient.deleteById(String.valueOf(id));
            solrClient.commit();
        } catch (SolrServerException |IOException e) {
            e.printStackTrace();
            return new ResultBean<>("500","同步数据失败");
        }
        return new ResultBean<>("200","同步数据成功");
    }


    public ResultBean quertByKeywords(String keywords) {
        SolrQuery query=new SolrQuery();
        //如果用户没有输入查询条件，就推荐华为手机给他
        if (keywords==null||"".equals(keywords.trim())){
            query.setQuery("product_name:华为");
        }else {
            query.setQuery("product_name:"+keywords);
        }
        query.setHighlight(true);
        query.addHighlightField("product_name");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        List<TProduct> list=new ArrayList<>();
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList results = response.getResults();
            //获取高亮信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            for (SolrDocument document : results) {
                TProduct product=new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                product.setPrice(Long.parseLong(document.getFieldValue("product_price").toString()));
                product.setSalePoint((String) document.getFieldValue("product_sale_point"));
                product.setImages(document.getFieldValue("product_images").toString());
                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id"));
                List<String> productNameHighLighting = map.get("product_name");
                if(productNameHighLighting != null && productNameHighLighting.size() > 0){
                    product.setName(productNameHighLighting.get(0));
                }else{
                    product.setName(document.getFieldValue("product_name").toString());
                }
                list.add(product);
            }
        }  catch (SolrServerException |IOException e) {
            e.printStackTrace();
            return new ResultBean<>("500",null);
        }
        return new ResultBean<>("200",list);
    }

    public ResultBean quertByKeywords(String keywords,int pageIndex,int pageSize) {
        SolrQuery query=new SolrQuery();
        //如果用户没有输入查询条件，就推荐华为手机给他
        if (keywords==null||"".equals(keywords.trim())){
            query.setQuery("product_name:华为");
        }else {
            query.setQuery("product_name:"+keywords);
        }
        query.setHighlight(true);
        query.addHighlightField("product_name");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        query.setStart((pageIndex-1)*pageSize);
        query.setRows(pageSize);
        List<TProduct> list=new ArrayList<>();
        PageResultBean<TProduct> pageResultBean=new PageResultBean<>();
        long total=0L;
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList results = response.getResults();
            total=results.getNumFound();
            //获取高亮信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            for (SolrDocument document : results) {
                TProduct product=new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                product.setPrice(Long.parseLong(document.getFieldValue("product_price").toString()));
                product.setSalePoint((String) document.getFieldValue("product_sale_point"));
                product.setImages(document.getFieldValue("product_images").toString());
                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id"));
                List<String> productNameHighLighting = map.get("product_name");
                if(productNameHighLighting != null && productNameHighLighting.size() > 0){
                    product.setName(productNameHighLighting.get(0));
                }else{
                    product.setName(document.getFieldValue("product_name").toString());
                }
                list.add(product);
            }
        }  catch (SolrServerException |IOException e) {
            e.printStackTrace();
            return new ResultBean<>("500",null);
        }
        System.out.println(list.size());
        pageResultBean.setList(list);
        pageResultBean.setPageSize(pageSize);
        pageResultBean.setPageNum(pageIndex);
        pageResultBean.setPages((int) (total%pageSize==0?total/pageSize:(total/pageSize)+1));
        pageResultBean.setTotal(total);
        return new ResultBean<>("200",pageResultBean);
    }
}
