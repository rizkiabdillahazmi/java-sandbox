package com.rizkiaazmi.demojavathread;

public class SynchronizedCounter {
    private Long value = 0L;

    public synchronized void increment() {
        value++;
    }

    // OR
//    public void increment() {
//        synchronized (this) {
//            value++;
//        }
//    }

    public Long getValue() {
        return value;
    }
}
