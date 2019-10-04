package com.springbootproducerconsumer.thread.consume;

import com.springbootproducerconsumer.worker.Processor;
import lombok.Builder;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Builder
@Setter
@Component
@Scope("prototype")
public class Consumer implements Runnable {

    private final Processor processor;

    public Consumer(Processor processor) {
        this.processor = processor;
    }

    @Override
    public void run() {
        processor.decrementCounter();
    }
}
