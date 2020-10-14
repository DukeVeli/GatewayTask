package com.api.gateway.data.entity;

import com.api.gateway.data.dto.FixerDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    private LocalDate date;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_rate_id")
    private List<CurrencyRate> rates;


    public FixerRate(FixerDTO fixerDTO) {
        this.timestamp = fixerDTO.getTimestamp();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(fixerDTO.getDate(), formatter);
        DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.base = fixerDTO.getBase();

        List<CurrencyRate> currencyRates = new ArrayList<>();
        fixerDTO.getRates().forEach((k, v) -> {
            CurrencyRate currencyRate = new CurrencyRate();
            currencyRate.setCurrency(Currency.valueOf(k));
            currencyRate.setRate(Double.valueOf(v));
            currencyRates.add(currencyRate);
        });
        this.rates = currencyRates;
        System.out.println();
    }
}
