package com.springbootproducerconsumer.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "requestDetails")
@Getter
@Setter
public class RequestDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "increaseConsumerCount")
    private int increaseConsumerCount;
    @Column(name = "increaseProducerCount")
    private int increaseProducerCount;
    @Column(name = "createTime")
    private LocalDate createTime = LocalDate.now();

}
