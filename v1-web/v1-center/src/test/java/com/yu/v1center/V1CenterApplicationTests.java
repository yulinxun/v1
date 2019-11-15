package com.yu.v1center;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import com.yu.v1center.config.faout.FanoutSender;
import com.yu.v1center.config.simple.Sender;
import com.yu.v1center.config.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V1CenterApplicationTests {
	@Autowired
	private FastFileStorageClient client;
//	@Autowired
//	private TopicSender sender;
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
	@Test
	public void uploadTest() throws FileNotFoundException {
		File file=new File("D:\\project\\v1\\v1-web\\v1-center\\src\\main\\resources\\static\\4.txt");
		FileInputStream is=new FileInputStream(file);
		StorePath storePath = client.uploadFile(is,file.length(),"txt",null);
		System.out.println(storePath.getFullPath());
		System.out.println(storePath.getGroup());
		System.out.println(storePath.getPath());
		System.out.println("http://106.13.208.154/"+storePath.getFullPath());
	}
//	@Test
//	public void deletet(){
//		client.deleteFile("group1/M00/00/00/rBAABF26xYSANcjVABCgHzAESKE027.jpg");
//	}
////	@Test
//	public void TestSend(){
//		sender.send();
//	}

}
