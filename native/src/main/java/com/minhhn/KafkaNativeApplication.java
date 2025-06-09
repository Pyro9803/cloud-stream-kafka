package com.minhhn;

import com.minhhn.properties.CloudEventProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(CloudEventProperties.class)
public class KafkaNativeApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaNativeApplication.class);
    }
}
