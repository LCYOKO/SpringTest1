package test.juc.threadpool;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author l
 * @Date 2021/1/19 13:46
 * @Version 1.0
 */

public class MyThreadFactory implements ThreadFactory {
    private final String DEFAULT_NAME="thread";
    private  AtomicInteger sequense;
    private String  NAME_PREFIX=DEFAULT_NAME;
    private ThreadGroup group;
    private UncaughtExceptionHandler handler=new MyExceptionHandler();
    public MyThreadFactory(String namePrefix){
        sequense=new AtomicInteger(1);
        SecurityManager manager = System.getSecurityManager();
        group=manager==null?Thread.currentThread().getThreadGroup():manager.getThreadGroup();
    }
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, NAME_PREFIX + sequense.incrementAndGet());
       thread.setUncaughtExceptionHandler(handler);
        return thread;
    }
}
