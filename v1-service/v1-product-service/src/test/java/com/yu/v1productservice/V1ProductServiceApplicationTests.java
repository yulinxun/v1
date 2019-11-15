package com.yu.v1productservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yu.api.IProductService;
import com.yu.api.IProductTypeService;
import com.yu.entity.TProduct;
import com.yu.entity.TProductType;
import com.yu.vo.ProductVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class V1ProductServiceApplicationTests {
	@Autowired
	private IProductService productService;
	@Reference
	private IProductTypeService productTypeService;
	@Test
	public void contextLoads() {
		List<TProductType> list = productTypeService.getList();
		for (TProductType tProductType : list) {
			System.out.println(tProductType.getName());
		}
	}
	@Test
	public void pageTest() {
		PageInfo<TProduct> pageInfo = productService.getPageInfo(1, 1);
		System.out.println(pageInfo.getSize());
	}
	@Test
	public void add() {
		TProduct tProduct=new TProduct();
		tProduct.setName("华为");
		tProduct.setPrice(22L);
		tProduct.setSalePoint("xx");
		tProduct.setImages("xx");
		tProduct.setSalePrice(15L);
		tProduct.setTypeId(2);
		tProduct.setTypeName("xx");
		ProductVO vo=new ProductVO();
		vo.setProduct(tProduct);
		vo.setProductDesc("xxxx");
		System.out.println(productService.add(vo));

	}

}
