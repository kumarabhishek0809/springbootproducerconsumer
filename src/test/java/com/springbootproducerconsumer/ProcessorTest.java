package com.springbootproducerconsumer;

import com.springbootproducerconsumer.thread.consume.Consumer;
import com.springbootproducerconsumer.thread.produce.Producer;
import com.springbootproducerconsumer.worker.Processor;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

@Log4j2
public class ProcessorTest {


    @Test
    public void testProcessor(){

        Processor processor = new Processor();
        boolean exitProgram = Processor.counter.get() == 0 || Processor.counter.get() == 100;
        while (!exitProgram) {
            double randomDouble = Math.random();
            randomDouble = randomDouble * 50 + 1;
            int randomInt = (int) randomDouble;
            if (randomInt % 2 == 0) {

                Thread t = new Thread(new Consumer(processor));
                t.start();
            } else if (randomInt % 3 == 0) {

                processor.updateCounterValue(randomInt % 7);

            } else {
                new Thread(new Producer(processor)).start();
            }

            exitProgram = Processor.counter.get() == 0 || Processor.counter.get() == 100;
            if (exitProgram) break;
        }
        log.info("Exit Call MYsql");
        Assert.assertEquals(1,Processor.counter.get());
    }
}
