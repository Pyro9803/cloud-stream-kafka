package com.minhhn;

import com.minhhn.dto.CloudEvent;
import com.minhhn.dto.CloudEventData;
import com.minhhn.dto.DataDTO;
import com.minhhn.service.CloudEventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SendMessageTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StreamBridge bridge;

    @MockitoBean
    private CloudEventService service;

    @Test
    void testSendMessage_shouldCallStreamBridge() throws Exception {
        DataDTO request = new DataDTO("device123", "/camera/abc", 9999L);
        CloudEvent event = CloudEvent.builder()
                .id(UUID.randomUUID())
                .type("test-type")
                .source("test")
                .specVersion("1.0")
                .dataContentType("application/json")
                .data(CloudEventData.builder().deviceId(request.getDeviceId()).uri(request.getUri()).expiresAt(request.getExp()).build())
                .build();

        when(service.getEventData(anyString(), anyString(), anyLong()))
                .thenReturn(event);


        mockMvc.perform(post("/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "deviceId": "device123",
                                "uri": "/camera/abc",
                                "exp": 9999
                            }
                        """))
                .andExpect(status().isOk());

        verify(bridge, times(1)).send(eq("producer-out-0"), eq(event));
    }
}
