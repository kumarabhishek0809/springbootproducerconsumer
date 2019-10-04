package com.springbootproducerconsumer.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "RequestDetails")
@Getter
@Setter
public class RequestDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "increaseConsumerCount")
    private int increaseConsumerCount;
    @Column(name = "increaseProducerCount")
    private int increaseProducerCount;
}
