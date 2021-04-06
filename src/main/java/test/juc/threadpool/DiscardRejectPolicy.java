package test.juc.threadpool;

import java.util.concurrent.Executor;

/**
 * @Author l
 * @Date 2021/1/16 18:43
 * @Version 1.0
 */
public class DiscardRejectPolicy implements RejectPolicy {

   
    @Override
    /**
     * @Description //TODO
     * @Param task
     * @Param threadPool
     * @Return void
     **/
    public void reject(Runnable task, Executor threadPool) {
        System.out.println("hello");
    }
}
