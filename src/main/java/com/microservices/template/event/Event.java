package com.microservices.template.event;

public interface Event<T> {
    EventType getEventType();
    T getPayload();
}
