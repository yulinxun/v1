package simple;

import com.rabbitmq.client.*;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author yu
 * @date 2019/11/5 0005
 */
public class MyConsumer {
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
//        创建消费者对象
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message=new String(body);
                System.out.println("接收到的数据是："+message);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
