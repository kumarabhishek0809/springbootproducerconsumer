package com.springbootproducerconsumer.service.manager;

import com.springbootproducerconsumer.dto.ProducerConsumerRequest;
import com.springbootproducerconsumer.thread.consume.CreateConsumer;
import com.springbootproducerconsumer.thread.produce.CreateProducer;
import com.springbootproducerconsumer.worker.Processor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Log4j2
public class ProcessorManager implements IProcessorManager {

    @Autowired
    private Processor processor;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void process(int counter) {
        String updateCounterValue = processor.updateCounterValue(counter);
        log.info(updateCounterValue);
    }

    @Override
    public void shutDownProgram() {
        String updateCounterValue = processor.shutDownProgram();
        log.info(updateCounterValue);
    }


    @Override
    @Async
    public void process(ProducerConsumerRequest producerConsumerRequest) {
         /* threadPoolTaskExecutor.execute(applicationContext.getBean(CreateConsumer.class, producerConsumerRequest));
        threadPoolTaskExecutor.execute(applicationContext.getBean(CreateProducer.class, producerConsumerRequest));*/

        new Thread(applicationContext.getBean(CreateConsumer.class, producerConsumerRequest)).start();
        new Thread(applicationContext.getBean(CreateProducer.class, producerConsumerRequest)).start();
        log.info("Program Exit");
    }
}
