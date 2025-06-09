package com.minhhn.service;

import com.minhhn.dto.CloudEvent;
import com.minhhn.dto.CloudEventData;
import com.minhhn.properties.CloudEventProperties;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CloudEventService {
    private final CloudEventProperties properties;

    public CloudEventService(CloudEventProperties properties) {
        this.properties = properties;
    }

    public CloudEvent getEventData(String deviceId, String uri, Long exp) {
        CloudEventData data = CloudEventData.builder()
                .deviceId(deviceId)
                .uri(uri)
                .payloadContentType("application/json")
                .payloadBase64("Hello".getBytes())
                .expiresAt(exp)
                .build();

        return CloudEvent.builder()
                .type(properties.getType())
                .specVersion(properties.getSpecVersion())
                .source("cloud-stream-kafka")
                .id(UUID.randomUUID())
                .time(System.currentTimeMillis())
                .dataContentType(properties.getDataContentType())
                .data(data)
                .build();
    }
}
