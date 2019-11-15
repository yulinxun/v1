package com.yu.v1searchservice;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.ISearchService;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.TProduct;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class V1SearchServiceApplicationTests {
	@Autowired
	public SolrClient solrClient;
	@Reference
	private ISearchService searchService;
	@Test
	public void add(){
		ResultBean resultBean = searchService.synById(19L);
		System.out.println(resultBean.getData());
	}
	@Test
	public void addOrUpdateTest() {
		SolrInputDocument document=new SolrInputDocument();
		document.setField("id","1");
		document.setField("product_name","华为");
		document.setField("product_price","22");
		document.setField("product_sale_point","22");
		document.setField("product_images","22");

		try {
			solrClient.add(document);
			solrClient.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void deleteTest() {
		try {
			solrClient.deleteById("change.me");
			solrClient.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void queryTest() throws IOException, SolrServerException {
		SolrQuery query=new SolrQuery();
		query.setQuery("product_name:小米");
		QueryResponse response = solrClient.query(query);
		SolrDocumentList results = response.getResults();
		System.out.println(results.getNumFound());
		for (SolrDocument result : results) {
			System.out.println(result.get("product_name"));
		}

	}
	@Test
	public void testAllQuery(){
		ResultBean resultBean = searchService.synAllData();
		System.out.println(resultBean.getData().toString());
	}
	@Test
	public void delById(){
		searchService.delById((long) 22);
	}
	@Test
	public void selectByKeyWords(){
		ResultBean resultBean = searchService.quertByKeywords("华为");
		List<TProduct> list = (List<TProduct>) resultBean.getData();
		for (TProduct product : list) {
			System.out.println(product.getId());
			System.out.println(product.getName());
			System.out.println(product.getPrice());
			System.out.println(product.getImages());
			System.out.println(product.getSalePoint());
		}
	}

}
