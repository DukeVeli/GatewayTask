package com.api.gateway.service.impl;

import com.api.gateway.data.dto.FixerDTO;
import com.api.gateway.data.entity.Currency;
import com.api.gateway.data.entity.CurrencyRate;
import com.api.gateway.data.entity.FixerRate;
import com.api.gateway.data.repository.FixerRepository;
import com.api.gateway.service.FixerIOService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FixerIOServiceImpl implements FixerIOService {
    private final FixerRepository fixerRepository;
    private final RestTemplate restTemplate;

    @Value("${access_key}")
    private String accessKey;
    @Value("${fixer_end_point}")
    private String fixerEndPoint;

    public FixerIOServiceImpl(FixerRepository fixerRepository, RestTemplateBuilder restTemplateBuilder) {
        this.fixerRepository = fixerRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public FixerRate getLastRatesFromFixerIO() {
        return fixerRepository.findFirstByOrderByDateDesc();
    }

    @Override
    public List<CurrencyRate> getAllFromPeriod(int hours, Currency cur) {
        List<FixerRate> allByOrderByDateDesc = fixerRepository.findAllByOrderByDateDesc();
        int size = allByOrderByDateDesc.size();
        if (size>hours){
            allByOrderByDateDesc.subList(hours,size).clear();
        }
        List<CurrencyRate> currencyRates= new ArrayList<>();

        allByOrderByDateDesc.forEach(e->{
            List<CurrencyRate> list = e.getRates();

            CurrencyRate rate = new CurrencyRate();
            rate.setCurrency(cur);
            for (CurrencyRate currencyRate : list) {
                Currency currency = currencyRate.getCurrency();
                if (currencyRate.getCurrency().equals(cur)) {
                    rate.setRate(currencyRate.getRate());
                    rate.setId(currencyRate.getId());
                currencyRates.add(rate);
                    break;
                }
            }
        });

        return currencyRates;
    }

    @Override
    public void persistNewestRate() {

        String url = fixerEndPoint + "?access_key=" + accessKey;
        FixerDTO fixerDTO = restTemplate.getForObject(url, FixerDTO.class);
        System.out.println();
        FixerRate fixerRate = new FixerRate(fixerDTO);
        this.fixerRepository.saveAndFlush(fixerRate);

    }

    @Override
    public void checkDB() {
        if (this.fixerRepository.count()==0){
            this.persistNewestRate();
        }
    }

}

