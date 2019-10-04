package com.springbootproducerconsumer.thread.produce;

import com.springbootproducerconsumer.dto.ProducerConsumerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CreateProducer implements Runnable {

    @Autowired
    private ApplicationContext applicationContext;

    private ProducerConsumerRequest producerConsumerRequest;

    public CreateProducer(ProducerConsumerRequest producerConsumerRequest) {
        this.producerConsumerRequest = producerConsumerRequest;
    }


    @Override
    public void run() {
        for (int i = 0; i < producerConsumerRequest.getIncreaseProducerCount(); i++) {
            new Thread(applicationContext.getBean(Producer.class)).start();
        }

    }
}
