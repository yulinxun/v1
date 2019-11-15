package simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author yu
 * @date 2019/11/5 0005
 */
public class Producer {
    private static String QUEUE_NAME="simple-queue";
    public static void main(String[] args) throws IOException, TimeoutException {
//        创建连接，连接MQ服务器
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.13.208.154");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("java1907");
        connectionFactory.setUsername("yu");
        connectionFactory.setPassword("123");
//        创建连接对象
        Connection connection = connectionFactory.newConnection();
//        创建通道
        Channel channel = connection.createChannel();
//        声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for (int i = 1; i < 11; i++) {
            String message = "第"+i+"遍说我爱你";
//        发送消息到队列上
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        }
        System.out.println("消息发送成功");
    }
}
