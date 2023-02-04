package com.example.producer;

import com.example.producer.service.ProducerService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class KafkaProducerApplicationTest {

    @Autowired
    private ProducerService producerService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.topic-name}")
    private String topicName;

    @Test
    void cotextLoads() {
        producerService.sendMessage(topicName, "测试");
    }

    @Test
    void sendMessage1() {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topicName, 0, System.currentTimeMillis(), "topic-key", "测试");
        producerRecord.headers().add("user", "zhangsan".getBytes());
        producerService.sendMessage(producerRecord);
    }

    @Test
    void sendMessage2() {
        String event = "测试";
        Map<String, Object> map = new HashMap<>();
        map.put("user", "zhangsan");
        MessageHeaders headers = new MessageHeaders(map);
        Message<String> message = MessageBuilder.createMessage(event, headers);
        kafkaTemplate.setDefaultTopic(topicName);
        producerService.sendMessage(message);
    }
}
