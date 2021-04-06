package test.juc.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author l
 * @Date 2021/1/19 13:45
 * @Version 1.0
 */
public class MyRjectPolicy implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("hahaha");
    }
}
