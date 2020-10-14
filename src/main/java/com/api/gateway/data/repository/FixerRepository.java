package com.api.gateway.data.repository;


import com.api.gateway.data.entity.Currency;
import com.api.gateway.data.entity.CurrencyRate;
import com.api.gateway.data.entity.FixerRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixerRepository extends JpaRepository<FixerRate,Long> {

       List<FixerRate> findAllByOrderByDateDesc();

       FixerRate findFirstByOrderByDateDesc();
}
