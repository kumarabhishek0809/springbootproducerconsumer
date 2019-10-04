package com.springbootproducerconsumer.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "counterValue")
@Getter
@Setter
@Builder
public class CounterValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "counterValue")
    private int counterValue;
    @Column(name = "threadDetails")
    private String threadDetails;
    @Column(name = "createTime")
    private LocalDate createTime = LocalDate.now();

}
