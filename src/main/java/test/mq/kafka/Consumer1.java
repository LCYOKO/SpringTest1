package test.mq.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author l
 * @Date 2021/4/6 16:33
 * @Version 1.0
 */
public class Consumer1 {

    public static void  createBefore(Map<String,Object> props){
        props.put("bootstrap.server","192.168.102.132");
        props.put("group.id","1");
        //props.put("enable.auto")
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // val序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    public static void main(String[] args) {
        Map<String,Object> props=new HashMap<>();
        createBefore(props);
        Consumer consumer=new KafkaConsumer<>(props);
    }
}
