package com.api.gateway.controller;

import com.api.gateway.data.dto.XmlDtoCurrent;
import com.api.gateway.data.entity.CurrencyRate;
import com.api.gateway.error.Error;
import com.api.gateway.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/xml_api")
public class XmlApiController {
private final ClientService clientService;

    @PostMapping("/command")
    public CurrencyRate getCurrent (@RequestBody XmlDtoCurrent dto){

        CurrencyRate rate = clientService.getCurrentXML(dto);
        if (rate == null) {
            throw new Error("Error");
        }
        return rate;


    }
}
