package com.microservices.template.events.producer;

import com.microservices.template.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AbstractProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public AbstractProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T> void sendEvent(Event<T> event, String topic) {
        String key = UUID.randomUUID().toString(); // Or derive from the event
        kafkaTemplate.send(topic, key, event);
        System.out.println("Sent event to Kafka: " + event.getEventType() + " for entity: " + event.getPayload());
    }
}
