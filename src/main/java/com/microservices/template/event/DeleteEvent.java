package com.microservices.template.event;

public class DeleteEvent<T> implements Event<T> {
    private final T payload;

    public DeleteEvent(T payload) {
        this.payload = payload;
    }

    @Override
    public EventType getEventType() {
        return EventType.DELETED;
    }

    @Override
    public T getPayload() {
        return payload;
    }
}
