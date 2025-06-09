package com.minhhn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloudEvent {

    private String type;

    private String specVersion = "1.0";

    // (where the event come from, eg. "simulator", "DG"...)
    private String source;

    private UUID id;

    private Long time;

    private String dataContentType;

    private CloudEventData data;
}
