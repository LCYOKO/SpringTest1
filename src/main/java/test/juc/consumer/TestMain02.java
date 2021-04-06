package test.juc.consumer;

import com.sun.org.apache.bcel.internal.generic.NEW;
import sun.invoke.empty.Empty;
import test.constant.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author l
 * @create 2020-08-28-9:25
 */
public class TestMain02 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();
        ArrayList<Integer> list = new ArrayList<>();
        Consumer consumer = new Consumer(empty, full, lock, list);
        Consumer consumer1 = new Consumer(empty, full, lock, list);
        Consumer consumer2 = new Consumer(empty, full, lock, list);
        Producer producer = new Producer(empty, full, list, 10, lock);
        new Thread(consumer,"Consumer01").start();
        new Thread(consumer1,"Consumer02").start();
        new Thread(consumer2,"Consumer03").start();
        new Thread(producer,"Producer").start();

        Thread.sleep(10000);
    }

    static class Consumer implements Runnable{
        private Condition empty;
        private Condition full;
        private Lock lock;
        private List<Integer> list;
       public Consumer(Condition empty,Condition full,Lock lock,List<Integer> list){
            this.full=full;
            this.empty=empty;
            this.lock=lock;
            this.list=list;
       }
        @Override
        public void run() {
            while (true){
                     try {
                         lock.lock();
                         while (list.isEmpty()){
                             full.signalAll();
                             empty.await();
                         }
                         Integer e=list.get(list.size()-1);
                         list.remove(list.size()-1);
                         System.out.println(Thread.currentThread().getName()+"消费了"+e);
                         full.signalAll();
                     }catch (Exception e){

                     }
                     finally {
                         lock.unlock();
                     }

            }

        }
    }

    static class Producer implements Runnable{
        private Condition full;
        private Condition empty;
        private List<Integer> list;
        private int maxLength;
        private Lock lock;
        public Producer(Condition empty,Condition full,List<Integer> list,int maxLength,Lock lock){
            this.list=list;
            this.full=full;
            this.empty=empty;
            this.lock=lock;
            this.maxLength=maxLength;
        }
        @Override
        public void run() {
           while (Constants.max>0){
                 try {
                     lock.lock();
                     if(list.size()==maxLength){
                         empty.signalAll();
                         full.await();
                     }

                     list.add(Constants.max);
                     System.out.println(Thread.currentThread().getName()+"生产了"+Constants.max--);
                     empty.signalAll();
                 }catch (Exception e){

                 }
                 finally {
                     lock.unlock();
                 }
           }
        }
    }

}




