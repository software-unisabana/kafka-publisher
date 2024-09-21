package com.sacavix.service;

import com.sacavix.events.CustomerCreatedEvent;
import com.sacavix.events.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class
CustomerEventsService {

    @KafkaListener(
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "Bancolombia",
            topics = "crc")
            //topicPartitions = {@TopicPartition(topic = "${topic.customer.name:customers}", partitions = {"0", "1"})})
    public void consumer(Event<?> event) {
        if (event.getClass().isAssignableFrom(CustomerCreatedEvent.class)) {
            CustomerCreatedEvent customerCreatedEvent = (CustomerCreatedEvent) event;
            log.info("Received Customer created event .... with Id={}, data={}",
                    customerCreatedEvent.getId(),
                    customerCreatedEvent.getData().toString());
        }

    }


}
