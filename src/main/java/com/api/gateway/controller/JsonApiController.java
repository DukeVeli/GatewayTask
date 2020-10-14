package com.api.gateway.controller;

import com.api.gateway.data.dto.JasonHistoryDto;
import com.api.gateway.data.dto.JsonCurrentDto;
import com.api.gateway.data.entity.Client;
import com.api.gateway.data.entity.FixerRate;
import com.api.gateway.service.ClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ModelMapper modelMapper;

    @PostMapping("/current")
    public FixerRate getCurrent (@RequestBody JsonCurrentDto dto){

       FixerRate fixerRate = clientService.getCurrent(dto);

        return fixerRate;
    }

    @PostMapping("/history")
    public List<FixerRate> getHistory (@RequestBody JasonHistoryDto dto){
        List<FixerRate> fixerRates = clientService.getFixerRates(dto);

        return fixerRates;
    }
}
