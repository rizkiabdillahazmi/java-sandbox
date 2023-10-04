package com.rizkiaazmi.demojavathread;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    void mainThread() {
        var name = Thread.currentThread().getName();
        System.out.println(name);
    }

    @Test
    void createThread() {
        Runnable runnable = () -> System.out.println("Hello from thread : " + Thread.currentThread().getName());
        runnable.run();

        var thread = new Thread(runnable);
        // Asynchronous
        thread.start();

        System.out.println("Program Selesai");
    }

    @Test
    void threadSleep() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var thread = new Thread(runnable);
        // Asynchronous
        thread.start();
        System.out.println("Program Selesai");
        Thread.sleep(3_000);
    }

    @Test
    void threadJoin() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var thread = new Thread(runnable);
        // Asynchronous
        thread.start();
        System.out.println("Program Selesai");
        // Wait until all process done
        thread.join();
    }
}
