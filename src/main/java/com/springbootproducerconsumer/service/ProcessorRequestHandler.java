package com.springbootproducerconsumer.service;

import com.springbootproducerconsumer.dto.ProducerConsumerRequest;
import com.springbootproducerconsumer.dto.response.CustomResponse;
import com.springbootproducerconsumer.repository.ProducerConsumerRequestRepository;
import com.springbootproducerconsumer.service.manager.IProcessorManager;
import com.springbootproducerconsumer.service.mapper.ProducerConsumerRequestMapper;
import com.springbootproducerconsumer.service.validator.IProcessorValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@Log4j2
public class ProcessorRequestHandler implements IProcessorRequestHandler {

    @Autowired
    @Qualifier("processorManager")  //processorManagerExecutor
    private IProcessorManager processor;

    @Autowired
    private IProcessorValidator validator;

    //@Autowired
    private ProducerConsumerRequestRepository producerConsumerRequestRepository;

    @Autowired
    private ProducerConsumerRequestMapper producerConsumerRequestMapper;

    @Override
    public ResponseEntity<CustomResponse> updateCounter(int counter){
        HttpStatus responseStatus = HttpStatus.OK;
        CustomResponse customResponse = new CustomResponse();

        //producerConsumerRequestRepository.save(producerConsumerRequestMapper.map(producerConsumerRequest));

        if (validator.validate(counter)) {

            processor.process(counter);
        }
        return new ResponseEntity<>(customResponse, responseStatus);
    }

    @Override
    public ResponseEntity<CustomResponse> shutDownProgram(){
        HttpStatus responseStatus = HttpStatus.ACCEPTED;
        CustomResponse customResponse = new CustomResponse();
        processor.shutDownProgram();
        return new ResponseEntity<>(customResponse, responseStatus);
    }



    @Override
    public ResponseEntity<CustomResponse> updateThreads(ProducerConsumerRequest producerConsumerRequest) {

        HttpStatus responseStatus = HttpStatus.CREATED;
        CustomResponse customResponse = new CustomResponse();

        //producerConsumerRequestRepository.save(producerConsumerRequestMapper.map(producerConsumerRequest));

        if (validator.validate(producerConsumerRequest)) {

            try {
                processor.process(producerConsumerRequest);
            } catch (InterruptedException | ExecutionException e) {
                log.error(e);
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>(customResponse, responseStatus);
    }
}
