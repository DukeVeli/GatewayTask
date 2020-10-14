package com.api.gateway.data.repository;

import com.api.gateway.data.entity.Client;
import com.api.gateway.data.entity.RequestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findClient(String name);
    Optional<Client> findClientByRequestIdContains(RequestId requestId);
}
