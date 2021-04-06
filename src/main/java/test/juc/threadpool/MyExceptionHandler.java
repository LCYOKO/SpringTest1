package test.juc.threadpool;

/**
 * @Author l
 * @Date 2021/1/19 17:32
 * @Version 1.0
 */
public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("处理你嘛呢");
    }
}
