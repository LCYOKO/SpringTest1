package test.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author l
 * @create 2020-12-21-22:33
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("groupOne");

        // Specify name server addresses.
        consumer.setNamesrvAddr("localhost:9876");

        // Subscribe one more more topics to consume.
        consumer.subscribe("TopicTest", "*");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // Register callback to execute on arrival of messages fetched from brokers.
        //consumer.registerMessageListener(new MessageListenerConcurrently() {
        //
        //    @Override
        //    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
        //                                                    ConsumeConcurrentlyContext context) {
        //
        //        for(MessageExt msg:msgs){
        //            System.out.println(new String(msg.getBody()));
        //        }
        //        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        //    }
        //});
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                  for (MessageExt msg:list){
                      System.out.println(Thread.currentThread().getName()+"-------"+new String(msg.getBody()));
                  }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
