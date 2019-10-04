package com.springbootproducerconsumer.service.mapper;

import com.springbootproducerconsumer.dto.ProducerConsumerRequest;
import com.springbootproducerconsumer.entity.RequestDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProducerConsumerRequestMapper {

    @Mappings({
                @Mapping(source = "increaseProducerCount", target = "increaseProducerCount"),
                @Mapping(source = "increaseConsumerCount", target = "increaseConsumerCount")
    })
    RequestDetails map(ProducerConsumerRequest producerConsumerRequest);
}
