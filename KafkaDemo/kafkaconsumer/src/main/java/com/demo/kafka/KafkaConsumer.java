package com.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "demoTopic",
            groupId = "demoGroup")
    public void consumeDemoTopic(String message) {
        System.out.println("MESSAGE: " + message);
    }
}
