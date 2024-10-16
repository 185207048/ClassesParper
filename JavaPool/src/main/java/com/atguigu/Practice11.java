package com.atguigu;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 演示锁的降级
public class Practice11 {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
    private final ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private int sharedResource = 0;

    public void upgradeToWriteLockThenDowngradeToReadLock() {
        writeLock.lock();
        try {
            // Perform write operation
            sharedResource++;
            System.out.println(Thread.currentThread().getName() + " wrote: " + sharedResource);

            // Downgrade: release write lock and acquire read lock
            writeLock.unlock();
            readLock.lock();
            try {
                // Perform read operation safely after downgrade
                System.out.println(Thread.currentThread().getName() + " read after downgrade: " + sharedResource);
            } finally {
                readLock.unlock(); // Ensure read lock is released
            }
        } finally {
            // In case of exception, ensure write lock is released
            // (though in this example it's already unlocked before downgrade)
            // This block is here to illustrate best practice
        }
    }

    public static void main(String[] args) {
        Practice11 example = new Practice11();

        Runnable task = () -> {
            example.upgradeToWriteLockThenDowngradeToReadLock();
        };

        Thread thread = new Thread(task, "DowngradeThread");
        thread.start();
    }
}
