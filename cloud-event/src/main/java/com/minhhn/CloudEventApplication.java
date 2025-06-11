package com.minhhn;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import io.cloudevents.jackson.PojoCloudEventDataMapper;
import io.cloudevents.rw.CloudEventRWException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.URI;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
@SpringBootApplication
public class CloudEventApplication {

    private final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(CloudEventApplication.class);
    }

    @Bean
    public Supplier<CloudEvent> produceEvent() {
        return () -> {
            try {
                String data = "{\"message\":\"Open the door\"}";

                return CloudEventBuilder.v1()
                        .withId(UUID.randomUUID().toString())
                        .withType("sensor/door")
                        .withSource(URI.create("http://localhost"))
                        .withData(data.getBytes())
                        .build();
            } catch (Exception e) {
                log.error("Error creating event: {}", e.getMessage());
                return null;
            }
        };
    }

    @Bean
    public Consumer<CloudEvent> consumeEvent() {
        return cloudEvent -> {
            try {
                log.info("Received Cloud Event ID: {}", cloudEvent.getId());
                log.info("Event Type: {}", cloudEvent.getType());
                log.info("Event Source: {}", cloudEvent.getSource());

                PojoCloudEventData<?> eventData = cloudEvent.getData() != null ? PojoCloudEventDataMapper.from(mapper, Object.class).map(cloudEvent.getData()) : null;

                if (eventData != null) {
                    log.info("Received event: {}", eventData.getValue());
                } else {
                    log.warn("Data is null");
                }
            } catch (CloudEventRWException e) {
                log.error("Error consume event: {}", e.getMessage());
            }
        };
    }
}
