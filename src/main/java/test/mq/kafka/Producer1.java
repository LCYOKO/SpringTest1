package test.mq.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @Author l
 * @Date 2021/4/6 15:49
 * @Version 1.0
 */
public class Producer1 {

    public static void createBefore(Map<String, Object> props) {
        //kafka Server端 必须要有
        props.put("bootstrap.servers", "192.168.102.132:9092");
        // key序列化  必须要有
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // val序列化  必须要有
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //acks 等待所有副本响应
        props.put("acks", "all");
        //发送端的尝试次数
        props.put("retries", 0);
        //一批数据请求大小
        props.put("batch.size", 16384);
        //请求数据时延
        props.put("linger.ms", "1");
        //生产者Buffer
        props.put("buffer.memory", 33445532);
        //自定义分区策略
        props.put("partitioner.class","test.mq.kafka.CustomerPartition");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Map<String, Object> props = new HashMap<>();
        createBefore(props);
        Producer<String, String> producer = new KafkaProducer(props);
        for (int i = 0; i < 10; i++) {
         producer.send(new ProducerRecord<String, String>("test", "test-topic" + i), new Callback() {
             @Override
             public void onCompletion(RecordMetadata metadata, Exception exception) {

                     System.out.println(metadata);

             }
         });
        }
    }
}
