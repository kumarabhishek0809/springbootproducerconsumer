package com.springbootproducerconsumer.worker;

import com.springbootproducerconsumer.entity.CounterValue;
import com.springbootproducerconsumer.repository.CounterValueRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Log4j2
public class Processor {

    @Autowired
    private CounterValueRepository counterValueRepository;

    public static AtomicInteger counter = new AtomicInteger(50);
    final Lock lock = new ReentrantLock(true);

    //it cannot be static otherwise once it is exit it will never come back.
    private volatile boolean exit = isExit();

    public boolean isExit() {
        exit = Processor.counter.get() == 0 || Processor.counter.get() == 100;
        return exit;
    }


    public String incrementCounter() {
        String currentThreadName = Thread.currentThread().getName();
        String logging = "Program Exit Increment";
        if (!exit) {
            lock.lock();
            if (!exit) { //double checking was important while increase threads
                counter.incrementAndGet();
                logging = "Counter is Increased ::: " + counter.get() + " :::: " + currentThreadName;
                isExit();
            }
            lock.unlock();
        }

        log.info(logging);
        persistToDBWhenCounterIsZero(currentThreadName);
        return logging;
    }

    private String persistToDBWhenCounterIsZero(String currentThreadName) {
        if (exit) {
            counterValueRepository.save(CounterValue.builder().counterValue(counter.get()).threadDetails(currentThreadName).build());
        }
        return "";
    }

    public String decrementCounter() {
        String logging = "Program Exit Decrement";
        String currentThreadName = Thread.currentThread().getName();
        if (!exit) {
            lock.lock();
            if (!exit) {
                counter.getAndDecrement();
                logging = "Counter is decreased ::: " + counter.get() + " :::: " + currentThreadName;
                isExit();
            }
            lock.unlock();
        }
        log.info(logging);
        persistToDBWhenCounterIsZero(currentThreadName);
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
