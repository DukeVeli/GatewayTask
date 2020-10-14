package com.api.gateway.data.repository;


import com.api.gateway.data.entity.FixerRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixerRepository extends JpaRepository<FixerRate,Long> {

       List<FixerRate> getLastFromTime(int hour);
       FixerRate getLatest();

}
