package com.springbootproducerconsumer.service.validator;

import com.springbootproducerconsumer.dto.ProducerConsumerRequest;

public interface IProcessorValidator {

    boolean validate(ProducerConsumerRequest producerConsumerRequest);

    boolean validate(int counter);
}
