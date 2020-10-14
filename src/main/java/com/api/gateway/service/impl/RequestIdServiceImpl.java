package com.api.gateway.service.impl;

import com.api.gateway.data.entity.RequestId;
import com.api.gateway.data.repository.RequestIdRepository;
import com.api.gateway.error.Error;
import com.api.gateway.service.RequestIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestIdServiceImpl implements RequestIdService {
    RequestIdRepository requestIdRepository;

    @Override
    public boolean checkIfIdIsUsed(String id) {
        RequestId requestId=null;
        requestId=requestIdRepository.findFirstByRequestId(id).orElse(null);

        if (requestId!=null){
           return true;

        }/*else {
                requestId = new RequestId();
                requestId.setRequestId(id);
            requestIdRepository.saveAndFlush(requestId);
        }*/
        return false;

    }
}
