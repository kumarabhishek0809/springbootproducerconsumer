package com.springbootproducerconsumer.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProducerConsumerRequest {

    @ApiParam("Number of Producers to be increased")
    private int increaseProducerCount;
    @ApiParam("Number of Consumer to be increased")
    private int increaseConsumerCount;
}
