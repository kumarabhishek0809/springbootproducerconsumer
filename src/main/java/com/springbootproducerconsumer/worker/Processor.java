package com.springbootproducerconsumer.worker;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Log4j2
public class Processor {
    public static AtomicInteger counter = new AtomicInteger(10);
    final Lock lock = new ReentrantLock(true);
    final private Condition valueReachedExit = lock.newCondition();
    //it cannot be static otherwise once it is exit it will never come back.
    private volatile boolean exit = isExit();

    public boolean isExit() {
        exit = Processor.counter.get() == 0 || Processor.counter.get() == 100;
        return exit;
    }


    public String incrementCounter() {
        String logging = "Program Exit";
        if (!exit) {
            lock.lock();
            if (!exit) {
                counter.incrementAndGet();
                logging = "Counter is Increased ::: " + counter.get() + " :::: " + Thread.currentThread().getName();
                isExit();
            }
            lock.unlock();
        }

        log.info(logging);

        return logging;
    }

    public String decrementCounter() {
        String logging = "Program Exit";
        if (!exit) {
            lock.lock();
            if (!exit) {
                counter.getAndDecrement();
                logging = "Counter is decreased ::: " + counter.get() + " :::: " + Thread.currentThread().getName();
                isExit();
            }
            lock.unlock();
        }
        log.info(logging);
        return logging;
    }

    public String updateCounterValue(int valueToUpdate) {
        synchronized (Processor.class) {
            counter.set(valueToUpdate);
        }
        String logging = "updateCounterValue ::: " + counter.get() + " :::: " + Thread.currentThread().getName();
        log.info(logging);
        return logging;
    }

    public String shutDownProgram() {
        synchronized (Processor.class) {
            exit = true;
            counter.set(0);
        }
        return "Hard Reset ::: " + counter.get() + " :::: " + Thread.currentThread().getName();
    }

}
