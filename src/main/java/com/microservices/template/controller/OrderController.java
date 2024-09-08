package com.microservices.template.controller;

import com.microservices.template.event.Event;
import com.microservices.template.event.EventType;
import com.microservices.template.event.factory.EventFactory;
import com.microservices.template.event.producer.AbstractProducer;
import com.microservices.template.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final EventFactory<Order> eventFactory;
    private final AbstractProducer producer;

    public OrderController(EventFactory<Order> eventFactory, AbstractProducer producer) {
        this.eventFactory = eventFactory;
        this.producer = producer;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        Event<Order> event = eventFactory.createEvent(EventType.CREATED, order);
        producer.sendEvent(event);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
        Event<Order> event = eventFactory.createEvent(EventType.UPDATED, order);
        producer.sendEvent(event);
        return new ResponseEntity<>("Order updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrder(@RequestBody Order order) {
        Event<Order> event = eventFactory.createEvent(EventType.DELETED, order);
        producer.sendEvent(event);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }
}

