package com.kykapple.springbootplayground.distributedLock.application;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class StockService {

    private RedissonClient redissonClient;

    public StockService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void setStockAmount(String key, int amount) {
        redissonClient.getBucket(key).set(amount);
    }

    public int getStockAmount(String key) {
        return (int) redissonClient.getBucket(key).get();
    }

    public void decreaseStockAmountWithoutLocking(String key) {
        String worker = Thread.currentThread().getName();
        int amount = getStockAmount(key);

        if (amount == 0) {
            log.info("[{}] 현재 남은 재고가 없습니다.", worker);
            return;
        }

        log.info("[{}] 현재 진행중 : {}", worker, amount);
        setStockAmount(key, amount - 1);
    }

    public void decreaseStockAmountWithLocking(String key) {
        String worker = Thread.currentThread().getName();
        String lockName = key + ":lock";
        RLock lock = redissonClient.getLock(lockName);

        try {
            if (!lock.tryLock(1, 2, TimeUnit.SECONDS)) {
                return;
            }
            int amount = getStockAmount(key);

            if (amount == 0) {
                log.info("[{}] 현재 남은 재고가 없습니다.", worker);
                return;
            }

            log.info("[{}] 현재 진행중 : {}", worker, amount);
            setStockAmount(key, amount - 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
    }

}
