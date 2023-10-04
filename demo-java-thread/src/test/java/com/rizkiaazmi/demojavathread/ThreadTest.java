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
        System.out.println("Menunggu Selesai");
        // Wait until all process done
        thread.join();
        System.out.println("Program Selesai");
    }

    @Test
    void threadInterrupt() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        System.out.println("Menunggu Selesai");
        thread.join();
        System.out.println("Program Selesai");
    }

    @Test
    void threadInterruptCorrect() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                // manual check interrupted
                if (Thread.interrupted()) {
                    return;
                }
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        };
        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        System.out.println("Menunggu Selesai");
        thread.join();
        System.out.println("Program Selesai");
    }
}
