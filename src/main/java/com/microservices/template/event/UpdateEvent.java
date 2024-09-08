package com.microservices.template.event;

public class UpdateEvent<T> implements Event<T> {
    private final T payload;

    public UpdateEvent(T payload) {
        this.payload = payload;
    }

    @Override
    public EventType getEventType() {
        return EventType.UPDATED;
    }

    @Override
    public T getPayload() {
        return payload;
    }
}
