package com.api.gateway.controller;

import com.api.gateway.data.dto.JasonHistoryDto;
import com.api.gateway.data.dto.JsonCurrentDto;
import com.api.gateway.data.entity.CurrencyRate;
import com.api.gateway.error.Error;
import com.api.gateway.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/json_api")
public class JsonApiController {
    private final ClientService clientService;

    @PostMapping("/current")
    public CurrencyRate getCurrent(@RequestBody JsonCurrentDto dto) {
        CurrencyRate fixerRate = clientService.getCurrent(dto);
        if (fixerRate == null) {
            throw new Error("Error");
        }
        return fixerRate;
    }

    @PostMapping("/history")
    public List<CurrencyRate> getHistory(@RequestBody JasonHistoryDto dto) {
        List<CurrencyRate> fixerRates = clientService.getFixerRates(dto);
        if (fixerRates == null) {
            throw new Error("Error");
        }

        return fixerRates;
    }
}
