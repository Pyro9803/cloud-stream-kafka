package com.minhhn.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloudEventProperties {

    private String type;
    private String specVersion;
    private String dataContentType;
}
