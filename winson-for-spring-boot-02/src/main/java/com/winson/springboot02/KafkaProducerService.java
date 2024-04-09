package com.winson.springboot02;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author mike ian
 * @date 2024/4/9
 * @desc
 **/
@Service
public class KafkaProducerService {


    public void sendMessage() {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.2.241:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>("quickstart-events", "hello22", "world22");
        producer.send(record);
        producer.flush();

//        try  {
//            KafkaProducer<String, String> producer = new KafkaProducer<>(props);
//            ProducerRecord<String, String> record = new ProducerRecord<>("quickstart-events", "hello12", "world12");
//            producer.send(record);
//            System.out.println("Message sent successfully.");
//            producer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("send success ... ");


//        Properties props2 = new Properties();
//        props2.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.2.241:9092");
//        props2.put(ProducerConfig.ACKS_CONFIG, "all");
//        props2.put(ProducerConfig.RETRIES_CONFIG, 0);
//        props2.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
//        props2.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        props2.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//
//        try (KafkaProducer<String, String> producer2 = new KafkaProducer<>(props2)) {
//            ProducerRecord<String, String> record2 = new ProducerRecord<>("quickstart-events", "hello2", "world3");
//            producer2.send(record2);
//            System.out.println("Message sent successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}
