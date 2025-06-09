package com.minhhn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataDTO {
    private String deviceId;
    private String uri;
    private Long exp;
}
