package com.springbootproducerconsumer;

import com.springbootproducerconsumer.thread.consume.Consumer;
import com.springbootproducerconsumer.thread.produce.Producer;
import com.springbootproducerconsumer.worker.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.concurrent.*;

@Log4j2
public class ProcessorParallelTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        List<Callable<String>> taskList = new CopyOnWriteArrayList<>();
        Processor processor = new Processor();
        boolean exitProgram = Processor.counter.get() == 0 || Processor.counter.get() == 100;

        while (!exitProgram) {
            double randomDouble = Math.random();
            randomDouble = randomDouble * 50 + 1;
            int randomInt = (int) randomDouble;
            if (randomInt % 2 == 0) {
                new Thread(new Consumer(processor)).start();

            } else if (randomInt % 3 == 0) {
                processor.updateCounterValue(randomInt%9);
            } else {
                new Thread(new Producer(processor)).start();
            }

            List<Future<String>> futures = executorService.invokeAll(taskList);
            futures.forEach(ProcessorParallelTest::accept);

            exitProgram = Processor.counter.get() == 0 || Processor.counter.get() == 100;
            if (exitProgram) break;
        }
        log.info("Exit Call MYsql");
        executorService.shutdown();
    }

    private static void accept(Future<String> stringFuture) {
        try {
            log.info("" + stringFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
