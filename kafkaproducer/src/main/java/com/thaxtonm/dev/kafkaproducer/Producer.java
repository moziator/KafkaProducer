package com.thaxtonm.dev.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class Producer {
    
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "quickstart-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String data, Map<String, String> headers) {
        logger.info(String.format("### -> Producing message -> %s", data));
        logger.info(headers.toString());
        Message<byte[]> message = MessageBuilder
            .withPayload(data.getBytes(StandardCharsets.UTF_8))
            .setHeader(KafkaHeaders.TOPIC, TOPIC)
            .setHeader("deploymentId",headers.get("deploymentId"))
            .setHeader("processId",headers.get("processId"))
            .setHeader("eventName",headers.get("eventName"))
            .setHeader("processInstanceId",headers.get("processInstanceId"))
            .build();
        this.kafkaTemplate.send(message);
        logger.info(message.toString());
    }

}
