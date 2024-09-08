package com.microservices.template.events;

public interface Event<T> {
    EventType getEventType();
    T getPayload();
}
