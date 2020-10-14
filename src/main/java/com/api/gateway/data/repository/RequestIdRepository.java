package com.api.gateway.data.repository;

import com.api.gateway.data.entity.RequestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestIdRepository extends JpaRepository<RequestId,Long> {
    Optional<RequestId> findFirstByRequestId(String id);
}
