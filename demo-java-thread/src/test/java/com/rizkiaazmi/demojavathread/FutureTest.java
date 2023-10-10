package com.rizkiaazmi.demojavathread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

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

    @Test
    void invokeAll() throws InterruptedException, ExecutionException {

        var executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
            Thread.sleep(value * 500L);
            return String.valueOf(value);
        }).toList();

        List<Future<String>> futures = executor.invokeAll(callables);

        for (Future<String> value : futures) {
            System.out.println(value.get());
        }
    }

    // Data yang paling cepat akan dikembalikan
    @Test
    void invokeAny() throws InterruptedException, ExecutionException {

        var executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
            Thread.sleep(value * 500L);
            return String.valueOf(value);
        }).toList();

        String result = executor.invokeAny(callables);
        System.out.println(result);
    }
}
