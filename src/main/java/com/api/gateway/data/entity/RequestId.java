package com.api.gateway.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "request_ids")
public class RequestId extends BaseEntity {
    @Column(name = "request_id", nullable = false)
    private String requestId;

    @ManyToOne
    private Client client;
}
