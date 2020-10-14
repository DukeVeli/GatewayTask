package com.api.gateway.service;

import com.api.gateway.data.entity.FixerRate;

import java.util.List;

public interface FixerIOService {
   FixerRate getLastRatesFromFixerIO ();
   List<FixerRate> getAllFromPeriod(int hours);
   void persistNewestRate();

   void checkDB();
}
