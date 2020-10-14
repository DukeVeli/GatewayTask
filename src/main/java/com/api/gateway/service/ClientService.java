package com.api.gateway.service;

import com.api.gateway.data.dto.JasonHistoryDto;
import com.api.gateway.data.dto.JsonCurrentDto;
import com.api.gateway.data.entity.Client;
import com.api.gateway.data.entity.FixerRate;

import java.util.List;

public interface ClientService {
    Client getByName(String name);
    boolean checkIfRequestExist(String name);

    FixerRate getCurrent(JsonCurrentDto dto);

    List<FixerRate> getFixerRates(JasonHistoryDto dto);
}
