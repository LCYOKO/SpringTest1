package test.juc.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author l
 * @Date 2021/1/16 18:41
 * @Version 1.0
 */
public class MyThreadPool implements Executor {
    private int coreSize;
    private int maxSize;
    private BlockingQueue<Runnable> taskQueue;
    private RejectPolicy rejectPolicy;

    private AtomicInteger sequence=new AtomicInteger();
    @Override
    public void execute(Runnable command) {

    }
}
