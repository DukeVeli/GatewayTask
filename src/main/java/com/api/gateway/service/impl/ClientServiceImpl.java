package com.api.gateway.service.impl;

import com.api.gateway.data.dto.JasonHistoryDto;
import com.api.gateway.data.dto.JsonCurrentDto;
import com.api.gateway.data.entity.Client;
import com.api.gateway.data.entity.FixerRate;
import com.api.gateway.data.entity.RequestId;
import com.api.gateway.data.repository.ClientRepository;
import com.api.gateway.error.Error;
import com.api.gateway.service.ClientService;
import com.api.gateway.service.FixerIOService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final FixerIOService fixerIOService;

    @Override
    public Client getByName(String name) {
        return clientRepository.findClient(name).orElse(null);
    }

    @Override
    public boolean checkIfRequestExist(String name) {
        RequestId requestId= new RequestId();
        requestId.setRequestId(name);
        Client result = clientRepository.findClientByRequestIdContains(requestId).orElse(null);
        return result!=null;
    }

    @Override
    public FixerRate getCurrent(JsonCurrentDto dto) {
        RequestId requestId = new RequestId();
        requestId.setRequestId(dto.getRequestId());
        if (clientRepository.findClientByRequestIdContains(requestId).isPresent()){
            throw new Error("Id is in use");
        }

        fixerIOService.checkDB();

        return fixerIOService.getLastRatesFromFixerIO();
    }

    @Override
    public List<FixerRate> getFixerRates(JasonHistoryDto dto) {
        RequestId requestId = new RequestId();
        requestId.setRequestId(dto.getRequestId());
        if (clientRepository.findClientByRequestIdContains(requestId).isPresent()){
            throw new Error("Id is in use");
        }

        fixerIOService.checkDB();

        return fixerIOService.getAllFromPeriod(dto.getPeriod());
    }
}
