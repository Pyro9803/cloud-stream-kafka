package com.minhhn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloudEventData {

    private String deviceId;

    // MQTT topic to send the payload to
    private String uri;

    private String payloadContentType;

    private byte[] payloadBase64;

    private Long expiresAt;

    @Builder.Default
    private Long occurredAt = System.currentTimeMillis();
}
