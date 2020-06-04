package test.juc;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author l
 * @create 2020-06-02-15:58
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
