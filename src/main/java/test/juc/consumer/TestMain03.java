package test.juc.consumer;

import test.constant.Constants;

import java.util.concurrent.BlockingDeque;

/**
 * @author l
 * @create 2020-08-28-18:09
 */
public class TestMain03 {
    public static void main(String[] args) {
//        queue = new ArrayBlockingQueue<Integer>(10);
//
//        Consumer consumer = new Consumer(queue);
//        Consumer consumer1 = new Consumer(queue);
//        Consumer consumer2 = new Consumer(queue);
//        Producer producer = new Producer(queue ,10);
//        new Thread(consumer,"Consumer01").start();
//        new Thread(consumer1,"Consumer02").start();
//        new Thread(consumer2,"Consumer03").start();
//        new Thread(producer,"Producer").start();
//
//        Thread.sleep(10000);
        System.out.println('A'-'a');
    }

    static  class Consumer implements  Runnable{
        private BlockingDeque<Integer> queue;
        public Consumer(BlockingDeque<Integer> qeuue){
            this.queue=qeuue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Integer take = queue.take();
                    System.out.println(Thread.currentThread().getName()+"消费了"+take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer implements Runnable{
        private BlockingDeque<Integer> queue;

        public Producer(BlockingDeque<Integer> queue){
                 this.queue=queue;
        }
        @Override
        public void run() {
              while (Constants.max>100){
                   queue.offer(Constants.max);
                   System.out.println(Thread.currentThread().getName()+"已经生产了"+Constants.max);
              }
        }
    }
}
