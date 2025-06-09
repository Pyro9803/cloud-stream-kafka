package com.minhhn.config;

import com.minhhn.dto.CloudEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Configuration
public class KafkaStreamConfig {

    @Bean
    public Function<CloudEvent, Message<byte[]>> process() {
        return cloudEvent -> {
            log.info("{}", cloudEvent);
            return MessageBuilder.withPayload(cloudEvent.toString().getBytes()).build();
        };
    }

    @Bean
    public Consumer<Message<byte[]>> consumer() {
        return message -> {
            log.info("{}", message);
        };
    }
}
