package com.microservices.template.event.factory;

import com.microservices.template.event.*;

public class EventFactory<T> {

    public Event<T> createEvent(EventType eventType, T entity) {
        switch (eventType) {
            case CREATED:
                return new CreateEvent<>(entity);
            case UPDATED:
                return new UpdateEvent<>(entity);
            case DELETED:
                return new DeleteEvent<>(entity);
            default:
                throw new IllegalArgumentException("Unsupported event type: " + eventType);
        }
    }
}
