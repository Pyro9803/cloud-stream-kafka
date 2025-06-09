package com.minhhn.controller;

import com.minhhn.dto.CloudEvent;
import com.minhhn.dto.DataDTO;
import com.minhhn.service.CloudEventService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SendMessageController {

    private final StreamBridge bridge;
    private final CloudEventService service;

    public SendMessageController(StreamBridge bridge, CloudEventService service) {
        this.bridge = bridge;
        this.service = service;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody DataDTO data) {
        CloudEvent event = service.getEventData(data.getDeviceId(), data.getUri(), data.getExp());
        bridge.send("producer-out-0", event);
    }
}
