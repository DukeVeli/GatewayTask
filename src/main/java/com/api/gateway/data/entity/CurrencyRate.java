package com.api.gateway.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "currency_rates")
public class CurrencyRate extends BaseEntity {
    @Column(name = "currency", nullable = false)
    private Currency currency;
    @Column(name = "rate", nullable = false)
    private Double rate;
}
