package com.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/kafka")
@RestController
public class KafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;
    @GetMapping("/publish")
    public ResponseEntity<String> publish() {
        kafkaProducer.sendMessage("Message from kafka producer");
        return ResponseEntity.ok("message sent");
    }
}
