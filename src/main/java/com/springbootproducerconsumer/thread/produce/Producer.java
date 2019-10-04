package com.springbootproducerconsumer.thread.produce;

import com.springbootproducerconsumer.worker.Processor;
import lombok.Builder;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Builder
@Setter
@Component
@Scope("prototype")
public class Producer implements Runnable {

    private final Processor processor;

    public Producer(Processor processor) {
        this.processor = processor;
    }

    @Override
    public void run()  {
        processor.incrementCounter();
    }
}
