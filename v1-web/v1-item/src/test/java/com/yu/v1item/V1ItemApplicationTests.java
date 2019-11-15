package com.yu.v1item;






import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.IProductService;
import com.yu.entity.TProduct;
import com.yu.v1item.entity.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class V1ItemApplicationTests {
	@Autowired
	private Configuration configuration;
	@Reference
	private IProductService productService;
	@Test
	public void contextLoads() throws IOException, TemplateException {
		Template template = configuration.getTemplate("hello.ftl");
		Map<String,Object> data=new HashMap<>();
		data.put("student",new Student(3,"yu",new Date()));
//		List<TProduct> list = productService.getList();
//		data.put("list",list);
		data.put("null",null);
		data.put("money",7500);
		Writer out=new FileWriter("D:\\project\\v1\\v1-web\\v1-item\\src\\main\\resources\\static\\hello.html");
		template.process(data,out);
		System.out.println("生成静态页面成功");
	}
	@Test
	public void grabPage() throws IOException, URISyntaxException {
		CloseableHttpClient client = HttpClients.createDefault();
		String path="http://coolaf.com/tool/params?g=gtest&g2=gtest2";
//		HttpGet get=new HttpGet(path);
		URIBuilder builder=new URIBuilder(path);
//		builder.addParameter("username","yu");
		URI uri = builder.build();
		HttpGet get=new HttpGet(uri);
		CloseableHttpResponse response = client.execute(get);
		int code=response.getStatusLine().getStatusCode();
		if (code==200){
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			byte[] b=new byte[1024];
			int len;
			while ((len=is.read(b))!=-1){
				System.out.println(new String(b,0,len));
			}
		}else {
			log.error("获取连接失败");
		}
	}
	@Test
	public void testUtils(){
		System.out.println(com.yu.commons.util.HttpClientUtils.doGet("http://coolaf.com/tool/params?g=gtest&g2=gtest2"));
	}
}
