package com.naumov.lock;

import com.naumov.thread.NumberedThreadAware;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * - Mutex
 * - Deadlock-Free
 * - O(1) space for N threads
 * - плох в реальности из-за архитектурных особенностей работы с кэшами
 */
public class TASLock extends NumberedThreadAware implements Lock {
    private final AtomicBoolean atomicBoolean = new AtomicBoolean();

    @Override
    public void lock() {
        while (atomicBoolean.getAndSet(true) == true) {
            // busy waiting aka spinning
        }
    }

    @Override
    public void unlock() {
        atomicBoolean.set(false);
    }
}
