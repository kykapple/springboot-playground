package com.kykapple.springbootplayground.distributedLock.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StockServiceTest {

    @Autowired
    private StockService stockService;

    private String stockKey;

    private int workerCount;

    @BeforeEach
    void setUp() {
        stockKey = "stock:peanut";
        workerCount = 100;
        stockService.setStockAmount(stockKey, 50);
    }

    @Test
    void decreaseWithoutLocking() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(workerCount);

        List<Thread> workers = Stream.generate(() -> new Thread(new WorkerWithoutLock(countDownLatch)))
                .limit(workerCount)
                .collect(Collectors.toList());

        workers.forEach(Thread::start);
        countDownLatch.await();

        int stockAmount = stockService.getStockAmount(stockKey);
        assertThat(stockAmount).isZero();
    }

    @Test
    void decreaseWithLocking() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(workerCount);

        List<Thread> workers = Stream.generate(() -> new Thread(new WorkerWithLock(countDownLatch)))
                .limit(workerCount)
                .collect(Collectors.toList());

        workers.forEach(Thread::start);
        countDownLatch.await();

        int stockAmount = stockService.getStockAmount(stockKey);
        assertThat(stockAmount).isZero();
    }

    private class WorkerWithLock implements Runnable {

        private CountDownLatch countDownLatch;

        public WorkerWithLock(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            stockService.decreaseStockAmountWithLocking(stockKey);
            countDownLatch.countDown();
        }
    }

    private class WorkerWithoutLock implements Runnable {

        private CountDownLatch countDownLatch;

        public WorkerWithoutLock(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            stockService.decreaseStockAmountWithoutLocking(stockKey);
            countDownLatch.countDown();
        }
    }

}