package com.springbootproducerconsumer.worker;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Log4j2
public class Processor {
    public static AtomicInteger counter = new AtomicInteger(50);
    private static final ReentrantLock lock = new ReentrantLock();
    //it cannot be static otherwise once it is exit it will never come back.
    private AtomicBoolean exit = new AtomicBoolean(Processor.counter.get() == 0
            || Processor.counter.get() == 100);


    public String incrementCounter() {
        if (!exit.get()) {
            synchronized (lock) {
                if (!exit.get()) {
                    counter.getAndIncrement();
                    lock.notify();
                }
            }
        }
        String logging = "Counter is Increased ::: " + counter.get() + " :::: " + Thread.currentThread().getName();
        log.info(logging);
        return logging;
    }

    public String decrementCounter() {
        if (!exit.get()) {
            synchronized (lock) {
                if (!exit.get()) {
                    counter.getAndDecrement();
                }
                lock.notify();
            }
        }
        String logging = "Counter is decreased ::: " + counter.get() + " :::: " + Thread.currentThread().getName();
        log.info(logging);
        return logging;
    }

    public String updateCounterValue(int valueToUpdate) {
        if (!exit.get()) {
            synchronized (lock) {
                synchronized (Processor.class) {
                    counter.set(valueToUpdate);
                }
                lock.notify();
            }
        }
        String logging = "updateCounterValue ::: " + counter.get() + " :::: " + Thread.currentThread().getName();
        log.info(logging);
        return logging;
    }

    public String shutDownProgram() {
        synchronized (Processor.class) {
            exit = new AtomicBoolean(Boolean.TRUE);
            counter.set(0);
        }
        return "Hard Reset ::: " + counter.get() + " :::: " + Thread.currentThread().getName();
    }

}
