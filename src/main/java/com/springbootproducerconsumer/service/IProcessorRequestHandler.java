package com.springbootproducerconsumer.service;

import com.springbootproducerconsumer.dto.ProducerConsumerRequest;
import com.springbootproducerconsumer.dto.response.CustomResponse;
import org.springframework.http.ResponseEntity;

public interface IProcessorRequestHandler {
    ResponseEntity<CustomResponse> updateThreads(ProducerConsumerRequest producerConsumerRequest);

    ResponseEntity<CustomResponse> updateCounter(int counter);

    ResponseEntity<CustomResponse> shutDownProgram();
}
