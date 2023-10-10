package com.rizkiaazmi.demojavathread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    @Test
    void testFutureGet() throws ExecutionException, InterruptedException {

        var executor = Executors.newVirtualThreadPerTaskExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5_000);
            return "Hi";
        };

        Future<String> future = executor.submit(callable);
        System.out.println("future completed");

        while (!future.isDone()) {
            System.out.println("waiting future");
            Thread.sleep(1_000);
        }

        String value = future.get();
        System.out.println(value);
    }

    @Test
    void testFutureCancel() throws ExecutionException, InterruptedException {

        var executor = Executors.newVirtualThreadPerTaskExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5_000);
            return "Hi";
        };

        Future<String> future = executor.submit(callable);
        System.out.println("future completed");

        Thread.sleep(2_000);
        future.cancel(true);

        System.out.println(future.isCancelled());

        String value = future.get();
        System.out.println(value);
    }
}
