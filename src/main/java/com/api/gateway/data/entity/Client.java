package com.api.gateway.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client extends BaseEntity {
    @Column(name = "client", nullable = false, unique = true)
    private String client;

    @Column(name = "currency", nullable = false)
    @Enumerated
    private Currency currency;

    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RequestId> requestId;
}
