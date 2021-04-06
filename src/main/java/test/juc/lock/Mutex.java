package test.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author l
 * @create 2020-06-02-15:58
 * -------------------------------------Synchronized和Lock的区别-----------------------------------
 *  1） syn是jvm层面实现的关键字，lock是java实现的类
 *  2） syn只支持非公平锁，lock支持公平和非公平
 *  3） syn获取不到锁会直接阻塞挂起，而lock支持tryLock，获取不到直接返回
 *  4） syn阻塞后无法中断，lock可以
 *  5） syn获取到锁是无法感知的，lock可以通过isHeldCurrentThread
 *  6） syn不需要显示加锁和释放锁，编译器会自动同步块前面加mutexEntry指令和mutexExist
 *
 * ----------------------------------- AQS-----------------------------------------------------
 *
 *
 *
 */
public class Mutex implements Lock {
    private Sync syn;
    public  Mutex(){
        syn=new Sync();
    }
     private static final class  Sync extends AbstractQueuedSynchronizer{

         @Override
         protected boolean tryAcquire(int acquires) {
             final Thread current = Thread.currentThread();
             int c = getState();

             if (c == 0) {
                 if (!hasQueuedPredecessors() &&
                         compareAndSetState(0, acquires)) {
                     setExclusiveOwnerThread(current);
                     return true;
                 }
             }
             else if (current == getExclusiveOwnerThread()) {
                 int nextc = c + acquires;
                 if (nextc < 0)
                     throw new Error("Maximum lock count exceeded");
                 setState(nextc);
                 return true;
             }
             return false;
         }

         @Override
         protected boolean tryRelease(int arg) {
             int c = getState() - 1;
             if (Thread.currentThread() != getExclusiveOwnerThread())
                 throw new IllegalMonitorStateException();
             boolean free = false;
             if (c == 0) {
                 free = true;
                 setExclusiveOwnerThread(null);
             }
             setState(c);
             return free;
         }


     }

    @Override
    public void lock() {
            syn.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
         syn.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
