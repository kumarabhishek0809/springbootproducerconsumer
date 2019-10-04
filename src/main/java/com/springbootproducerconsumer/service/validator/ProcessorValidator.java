package com.springbootproducerconsumer.service.validator;

import com.springbootproducerconsumer.dto.ProducerConsumerRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProcessorValidator implements IProcessorValidator {

    @Override
    public boolean validate(int counter){
        return counter > 0;
    }

    @Override
    public boolean validate(ProducerConsumerRequest producerConsumerRequest){
        log.info("Validated");
        if(producerConsumerRequest != null ){
            if(producerConsumerRequest.getIncreaseProducerCount() == 0 && producerConsumerRequest.getIncreaseConsumerCount() == 0 ){
                return false;
            }
        }
        return true;
    }
}
