package com.springbootproducerconsumer.service.manager;

import com.springbootproducerconsumer.dto.ProducerConsumerRequest;

import java.util.concurrent.ExecutionException;

public interface IProcessorManager {
    void process(ProducerConsumerRequest producerConsumerRequest) throws InterruptedException, ExecutionException;

    void process(int counter);

    void shutDownProgram();
}
