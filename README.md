
# Generic Kafka Producer with Events

This project demonstrates a **generic Kafka producer** implementation using **Spring Boot**. The producer handles events for different entity types (aggregates) such as `Order`, `Product`, etc., and can handle the following events:
- **Create Event**
- **Update Event**
- **Delete Event**

This design is based on the **Strategy** and **Factory** patterns to enable flexibility and reuse, allowing any entity to be passed as a generic, while the event handling logic is abstracted.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Usage](#usage)
- [Setup and Run](#setup-and-run)
- [Kafka Event Implementation](#kafka-event-implementation)
- [License](#license)

## Features
- **Generic Kafka Producer**: Can handle any type of aggregate (entity) by passing the entity as a generic type.
- **Event Types**: Supports `Created`, `Updated`, and `Deleted` event types.
- **Dynamic Event Creation**: Events are created dynamically using a **Factory Pattern**.
- **Configuration**: Kafka is easily configurable through Spring Boot's `application.properties` or `application.yml`.

## Technologies
- **Java 17**
- **Spring Boot 3.x**
- **Apache Kafka**
- **Strategy & Factory Design Patterns**

## Usage

### 1. Define Your Entity
You can define any entity or aggregate, such as an `Order`, `Product`, or `Customer`:

```java
public class Order {
    private String orderId;
    private String productName;
    private int quantity;

    // Getters and setters...
}
```

### 2. Create a Service to Send Events
You can create events (create, update, delete) for your entity using the `KafkaEventFactory` and send them using `GenericKafkaProducer`.

```java
@Service
public class OrderService {
    private final GenericKafkaProducer kafkaProducer;
    private final KafkaEventFactory<Order> eventFactory;

    @Autowired
    public OrderService(GenericKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        this.eventFactory = new KafkaEventFactory<>();
    }

    public void createOrder(Order order) {
        KafkaEvent<Order> event = eventFactory.createEvent(EventType.CREATED, order);
        kafkaProducer.sendEvent(event, "order-topic");
    }

    public void updateOrder(Order order) {
        KafkaEvent<Order> event = eventFactory.createEvent(EventType.UPDATED, order);
        kafkaProducer.sendEvent(event, "order-topic");
    }

    public void deleteOrder(Order order) {
        KafkaEvent<Order> event = eventFactory.createEvent(EventType.DELETED, order);
        kafkaProducer.sendEvent(event, "order-topic");
    }
}
```

### 3. Kafka Event Implementation

The project implements three core event types:
- **CreateEvent<T>**
- **UpdateEvent<T>**
- **DeleteEvent<T>**

These events are sent through the generic Kafka producer using the event type and entity.

## Setup and Run

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/generic-kafka-producer.git
cd generic-kafka-producer
```

### 2. Configure Kafka
Update the `application.properties` with the Kafka broker details:
```properties
spring.kafka.bootstrap-servers=localhost:9092
```

### 3. Run the Application
You can run the Spring Boot application using Maven or your favorite IDE:
```bash
mvn spring-boot:run
```

Ensure that you have a Kafka broker running at `localhost:9092`. You can set it up using Docker:
```bash
docker run -d --name kafka -p 9092:9092 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_ZOOKEEPER_CONNECT=localhost:2181 confluentinc/cp-kafka:latest
```

### 4. Use the Service
You can now call the `OrderService` methods to send create, update, and delete events to Kafka.

## License
This project is licensed under the MIT License.
