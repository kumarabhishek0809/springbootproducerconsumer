package com.springbootproducerconsumer.thread.consume;

import com.springbootproducerconsumer.dto.ProducerConsumerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CreateConsumer implements Runnable {

    @Autowired
    private ApplicationContext applicationContext;

    private ProducerConsumerRequest producerConsumerRequest;

    public CreateConsumer(ProducerConsumerRequest producerConsumerRequest) {
        this.producerConsumerRequest = producerConsumerRequest;
    }

    @Override
    public void run() {
        for (int i = 0; i < producerConsumerRequest.getIncreaseConsumerCount(); i++) {
            new Thread (applicationContext.getBean(Consumer.class)).start();
        }
    }
}
