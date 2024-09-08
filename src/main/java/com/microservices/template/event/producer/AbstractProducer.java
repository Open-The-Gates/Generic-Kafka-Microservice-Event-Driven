package com.microservices.template.event.producer;

import com.microservices.template.event.Event;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AbstractProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public AbstractProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T> void sendEvent(Event<T> event) {
        String key = UUID.randomUUID().toString(); // Or derive from the event
        kafkaTemplate.send(event.getPayload().getClass().getSimpleName(), key, event);
        System.out.println("Sent event to Broker: " + event.getEventType() + " for entity: " + event.getPayload());
    }
}
