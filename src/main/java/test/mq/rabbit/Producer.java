package test.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author l
 * @Date 2021/4/2 0:00
 * @Version 1.0
 */
public class Producer {

    static final String EXCHANGE_NAME = "fanout_test";
    static final String EXCHANGE_TYPE = "fanout";

    static final String D_EXCHANGE_NAME = "direct_test";
    static final String D_EXCHANGE_TYPE = "direct";

    static final String T_EXCHANGE_NAME = "";
    static final String T_EXCHANGE_TYPE = "topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机地址;默认为 localhost
        connectionFactory.setHost("localhost");
        //连接端口;默认为 5672
        connectionFactory.setPort(5672);
        //虚拟主机名称;默认为 /
        connectionFactory.setVirtualHost("/");
        //连接用户名；默认为guest
        connectionFactory.setUsername("guest");
        //连接密码；默认为guest
        connectionFactory.setPassword("guest");

        //创建连接
        Connection connection = connectionFactory.newConnection();

        // 创建频道
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        /**
         * 参数1：队列名称
         * 参数2：是否定义持久化队列
         * 参数3：是否独占本次连接
         * 参数4：是否在不使用的时候自动删除队列
         * 参数5：队列其它参数
         */
    //    channel.queueDeclare(QUEUE_NAME, true, false, false, null);
      //  channel.exchangeDeclare(EXCHANGE_NAME,EXCHANGE_TYPE);

        channel.queueDeclare(Consumer.FANOUT_NAME1, true, false, false, null);
        channel.queueDeclare(Consumer.FANOUT_NAME2, true, false, false, null);
        channel.exchangeDeclare(D_EXCHANGE_NAME,D_EXCHANGE_TYPE);

        channel.queueBind(Consumer.FANOUT_NAME1,D_EXCHANGE_NAME,"test1");
        channel.queueBind(Consumer.FANOUT_NAME2,D_EXCHANGE_NAME,"test2");
        // 要发送的信息
        String message = "你好；小兔子！";
        /**
         * 参数1：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数2：路由key,简单模式可以传递队列名称
         * 参数3：消息其它属性
         * 参数4：消息内容
         */
        channel.confirmSelect()
        for(int i=0;i<10;i++){
            channel.basicPublish(D_EXCHANGE_NAME,"test1",null,(message+i).getBytes());
        }
        for(int i=10;i<20;i++){
            channel.basicPublish(D_EXCHANGE_NAME,"test2",null,(message+i).getBytes());
        }
      //  channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("已发送消息：" + message);

        // 关闭资源
        channel.close();
        connection.close();
    }
}
