package com.api.gateway.data.dto;

import com.api.gateway.data.entity.Currency;
import lombok.Data;

import java.util.Map;

@Data
public class FixerDTO {
    private long timestamp;
    private Currency base;
    private String date;
    private Map<String,Double> rates;
}
