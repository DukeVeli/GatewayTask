package com.api.gateway.data.dto;

import com.api.gateway.data.entity.Currency;
import lombok.Data;

@Data
public class JsonCurrentDto {
    private String requestId;
    private int timestamp;
    private String client;
    private Currency currency;
}
