package topic;

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
    private static String EXCHANGE_NAME = "topic_exchange2";

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
//        声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String message1 = "体育新闻";
        String message2 = "游戏资讯";
        String message3 = "中国体育赛事";
//        发送消息到队列上
        channel.basicPublish(EXCHANGE_NAME, "path.All", null, message1.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "path.China", null, message3.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "game", null, message2.getBytes());
        System.out.println("消息发送成功");
    }
}
