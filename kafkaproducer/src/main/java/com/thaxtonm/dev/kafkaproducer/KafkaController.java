package com.thaxtonm.dev.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/kafka")
public class KafkaController {
    
    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) { this.producer = producer;}

    @PostMapping(value="/publish")
    public String sendMessageToKafkaTopic(@RequestBody String message,
                                        @RequestParam("deploymentId") String deploymentId,
                                        @RequestParam("processId") String processId,
                                        @RequestParam("eventName") String eventName,
                                        @RequestParam("processInstanceId") String processInstanceId) {
                                            Map<String, String> headers = new HashMap<>();
                                            headers.put("deploymentId",deploymentId);
                                            headers.put("processId",processId);
                                            headers.put("eventName",eventName);
                                            headers.put("processInstanceId",processInstanceId);
                                            this.producer.sendMessage(message, headers);
                                            return "Message sent to Kafka.  Thanks!";
                                        }
}
