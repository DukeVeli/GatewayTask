package com.api.gateway.service.impl;

import com.api.gateway.data.dto.FixerDTO;
import com.api.gateway.data.entity.FixerRate;
import com.api.gateway.data.repository.FixerRepository;
import com.api.gateway.service.FixerIOService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        return fixerRepository.getLatest();
    }

    @Override
    public List<FixerRate> getAllFromPeriod(int hours) {
        return fixerRepository.getLastFromTime(hours);
    }

    @Override
    public void persistNewestRate() {

        String url = fixerEndPoint + "?access_key=" + accessKey;
        FixerDTO fixerDTO = restTemplate.getForObject(url, FixerDTO.class);
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

