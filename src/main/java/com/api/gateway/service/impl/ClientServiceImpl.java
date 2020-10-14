package com.api.gateway.service.impl;

import com.api.gateway.data.dto.JasonHistoryDto;
import com.api.gateway.data.dto.JsonCurrentDto;
import com.api.gateway.data.entity.*;
import com.api.gateway.data.repository.ClientRepository;
import com.api.gateway.error.Error;
import com.api.gateway.service.ClientService;
import com.api.gateway.service.FixerIOService;
import com.api.gateway.service.RequestIdService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final FixerIOService fixerIOService;
    private final RequestIdService requestIdService;
    private final ModelMapper modelMapper;

    @Override
    public Client getByName(String name) {
        return clientRepository.findClientByClient(name).orElse(null);
    }


    @Override
    public CurrencyRate getCurrent(JsonCurrentDto dto) {
        if (requestIdService.checkIfIdIsUsed(dto.getRequestId())) {
            //throw  new Error("Error");
            return null;
        }

        RequestId requestId = new RequestId();
        requestId.setRequestId(dto.getRequestId());

        fixerIOService.checkDB();
        persistPerson(dto);


        FixerRate lastRatesFromFixerIO = fixerIOService.getLastRatesFromFixerIO();
        List<CurrencyRate> list = lastRatesFromFixerIO.getRates();

        CurrencyRate rate = new CurrencyRate();
        rate.setCurrency(dto.getCurrency());
        for (CurrencyRate currencyRate : list) {
            Currency currency = currencyRate.getCurrency();
            if (currencyRate.getCurrency().equals(dto.getCurrency())) {
                rate.setRate(currencyRate.getRate());
                rate.setId(currencyRate.getId());
                break;
            }
        }

        return rate;
    }

    @Override
    public List<CurrencyRate> getFixerRates(JasonHistoryDto dto) {
        if (requestIdService.checkIfIdIsUsed(dto.getRequestId())) {
            //throw  new Error("Error");
            return null;
        }

        RequestId requestId = new RequestId();
        requestId.setRequestId(dto.getRequestId());

        fixerIOService.checkDB();
        JsonCurrentDto dto1 = new JsonCurrentDto();
        modelMapper.map(dto, dto1);

        persistPerson(dto1);

        return fixerIOService.getAllFromPeriod(dto.getPeriod(), dto.getCurrency());
    }

    private void persistPerson(JsonCurrentDto dto) {
        Client client = clientRepository.findClientByClient(dto.getClient()).orElse(null);
        if (client == null) {
            client = new Client();
            client.setClient(dto.getClient());
            client.setCurrency(dto.getCurrency());
            client.setTimestamp(dto.getTimestamp());
            RequestId requestId = new RequestId();
            requestId.setRequestId(dto.getRequestId());
            requestId.setClient(client);
            client.setRequestId(new ArrayList<>());
            client.getRequestId().add(requestId);

        } else {
            RequestId requestId = new RequestId();
            requestId.setRequestId(dto.getRequestId());
            requestId.setClient(client);
            client.getRequestId().add(requestId);
        }
        clientRepository.saveAndFlush(client);
    }


}
