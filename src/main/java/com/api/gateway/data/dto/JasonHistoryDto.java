package com.api.gateway.data.dto;

import com.api.gateway.data.entity.Currency;
import lombok.Data;

@Data
public class JasonHistoryDto {
    private String requestId;
    private long timestamp;
    private String client;
    private Currency currency;
    private int period;
}
