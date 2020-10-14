package com.api.gateway.controller;

import com.api.gateway.data.dto.XmlDto;
import com.api.gateway.data.entity.Client;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/xml_api")
public class XmlApiController {

    @PostMapping("/command")
    public Client getCurrent (@RequestBody XmlDto dto){



        return null;
    }
}
