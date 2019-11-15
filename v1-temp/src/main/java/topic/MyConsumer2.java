package topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author yu
 * @date 2019/11/5 0005
 */
public class MyConsumer2 {
    private static String EXCHANGE_NAME = "topic_exchange2";
    private static String QUEUE_NAME="topic_exchange-queue-2";
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
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
//        声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"game");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"path.*");
//        创建消费者对象
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message=new String(body);
                System.out.println("消费者2：接收到的数据是："+message);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
