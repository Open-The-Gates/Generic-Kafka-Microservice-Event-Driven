package com.microservices.template.event;

public class CreateEvent<T> implements Event<T> {
    private final T payload;

    public CreateEvent(T payload) {
        this.payload = payload;
    }

    @Override
    public EventType getEventType() {
        return EventType.CREATED;
    }

    @Override
    public T getPayload() {
        return payload;
    }
}
