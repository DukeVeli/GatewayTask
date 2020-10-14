package com.api.gateway.data.entity;

import com.api.gateway.data.dto.FixerDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fixer_rates")
public class FixerRate extends BaseEntity {

    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @Column(name = "base", nullable = false)
    @Enumerated
    private Currency base;

    @Column(name = "date", nullable = false)
    private Date date;

    @OneToMany
    @JoinColumn(name = "currency_rate_id")
    private List<CurrencyRate> rates;


    public FixerRate(FixerDTO fixerDTO) {
        this.timestamp = fixerDTO.getTimestamp();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = format.parse(fixerDTO.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.base=fixerDTO.getBase();

        List<CurrencyRate> currencyRates= new ArrayList<>();
        fixerDTO.getRates().forEach((k,v)->{
            CurrencyRate currencyRate= new CurrencyRate();
            currencyRate.setCurrency(Currency.valueOf(k));
            currencyRate.setRate(v);
            currencyRates.add(currencyRate);
        });
        this.rates=currencyRates;
    }
}
