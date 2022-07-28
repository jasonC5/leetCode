package com.jason.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenjieaj
 * @date 2022/7/8 10:16:33
 * @description
 */
public class Test2 {

    //    public static class ITL extends InheritableThreadLocal<String> {
//
//    }
    // 可遗传ThreadLocal
    public static final InheritableThreadLocal<String> ITL = new InheritableThreadLocal<String>();
    public static final ThreadLocal<String> TL = new ThreadLocal<String>();

    public static final Runnable printThreadName = new Runnable() {
        AtomicInteger callCount = new AtomicInteger();

        @Override
        public void run() {
            String s = "callCount::" + callCount.getAndIncrement() + ", run thread::" + Thread.currentThread().getName() + ", ITL=" + ITL.get() + ", TL=" + TL.get();
//            if (TL.get()==null) {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            System.out.println(s);
        }
    };

    public static void main(String[] args) throws InterruptedException {
        ITL.set("Main Thread InheritableThreadLocal");
        TL.set("ThreadLocal");
        ExecutorService executorService = new ThreadPoolExecutor(0, 100, 0, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
//                new LinkedBlockingQueue<>(),
//                new ThreadFactory() {
//                    final AtomicInteger ai = new AtomicInteger();
//                    @Override
//                    public Thread newThread(Runnable r) {
//                        Thread thread = new Thread(r);
//                        thread.setName("pool-Thread-" + ai.getAndIncrement());
//                        System.out.println("new Thread:" + thread.getName());
//                        return thread;
//                    }
//                },
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 2000; i++) {
            executorService.submit(printThreadName);
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

    }

    public class CrmCompletableFuture extends CompletableFuture{

    }
}
