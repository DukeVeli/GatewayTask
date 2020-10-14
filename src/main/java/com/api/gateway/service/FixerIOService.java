package com.api.gateway.service;

import com.api.gateway.data.entity.Currency;
import com.api.gateway.data.entity.CurrencyRate;
import com.api.gateway.data.entity.FixerRate;

import java.util.List;

public interface FixerIOService {
   FixerRate getLastRatesFromFixerIO ();
   List<CurrencyRate> getAllFromPeriod(int hours, Currency currency);
   void persistNewestRate();

   void checkDB();
}
