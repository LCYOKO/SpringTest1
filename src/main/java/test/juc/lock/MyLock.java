package test.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author l
 * @Date 2021/2/4 16:05
 * @Version 1.0
 */
public class MyLock implements Lock {

    private Mutex mutexLock;

    @Override
    public void lock() {
            mutexLock.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return mutexLock.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mutexLock.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        mutexLock.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public  class Mutex extends AbstractQueuedSynchronizer{

        public void lock(){
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
            }
            acquire(1);
        }
        @Override
        protected boolean tryAcquire(int arg) {
            final Thread currentThread=Thread.currentThread();
            int c=getState();
            if(c==0){
                if(compareAndSetState(0,1)){
                    setExclusiveOwnerThread(currentThread);
                    return true;
                }
                return false;
            }
            if(currentThread==getExclusiveOwnerThread()){
                 c+=arg;
                 if(c<=0){
                     throw  new RuntimeException("error");
                 }
                 setState(c);
                 return  true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int c=getState()-arg;
            if(Thread.currentThread()!=getExclusiveOwnerThread()){
                throw new RuntimeException("error");
            }
            boolean free=false;
            if(c==0){
                  free=true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }

        //@Override
        //protected int tryAcquireShared(int arg) {
        //    return super.tryAcquireShared(arg);
        //}
        //
        //@Override
        //protected boolean tryReleaseShared(int arg) {
        //    return super.tryReleaseShared(arg);
        //}

        @Override
        protected boolean isHeldExclusively() {
            return Thread.currentThread()==getExclusiveOwnerThread();
        }
    }
}
