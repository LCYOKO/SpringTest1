package test.juc.threadpool;

import java.util.concurrent.Executor;

/**
 * @Author l
 * @Date 2021/1/16 18:42
 * @Version 1.0
 */
public interface RejectPolicy {
    void reject(Runnable task, Executor threadPool);
}
