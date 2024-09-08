package com.microservices.template.service;

import com.microservices.template.event.Event;
import com.microservices.template.event.EventType;
import com.microservices.template.event.factory.EventFactory;
import com.microservices.template.event.producer.AbstractProducer;
import com.microservices.template.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final AbstractProducer producer;
    private final EventFactory<Order> eventFactory;

    public OrderService(AbstractProducer producer) {
        this.producer = producer;
        this.eventFactory = new EventFactory<>();
    }

    public void createOrder(Order order) {
        Event<Order> event = eventFactory.createEvent(EventType.CREATED, order);
        producer.sendEvent(event);
    }

    public void updateOrder(Order order) {
        Event<Order> event = eventFactory.createEvent(EventType.UPDATED, order);
        producer.sendEvent(event);
    }

    public void deleteOrder(Order order) {
        Event<Order> event = eventFactory.createEvent(EventType.DELETED, order);
        producer.sendEvent(event);
    }
}