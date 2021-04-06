package test.juc.consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author l
 * @create 2020-08-27-21:48
 */
public class TestMain01 {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();

        Consumer consumer = new Consumer(list);
        Consumer consumer1 = new Consumer(list);
        Consumer consumer2 = new Consumer(list);
        Producer producer = new Producer(list, 10);
        new Thread(consumer,"Consumer-1").start();
        new Thread(consumer1,"consumer-2").start();
        new Thread(consumer2,"consumer-3").start();
        new Thread(producer,"producer").start();
        Thread.sleep(10000);
    }



    private static class Consumer implements  Runnable{
        private List<Integer> queue;

        public Consumer(List<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    while (queue.isEmpty()){
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Integer integer = queue.get(0);
                    queue.remove(0);
                    System.out.println(Thread.currentThread().getName()+"消费了"+integer);
                    queue.notifyAll();
                }
            }
        }
    }



    static class Producer implements Runnable{
        private List<Integer> queue;
        private int maxLength;
        private int max=1000;

        public Producer(List<Integer> queue, int maxLength) {
            this.queue = queue;
            this.maxLength = maxLength;
        }

        @Override
        public void run() {
            while (max>0){
                synchronized (queue){
                    while (queue.size()==maxLength){
                        queue.notifyAll();
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(max);
                    System.out.println(Thread.currentThread().getName()+"生产了"+max--);
                    queue.notifyAll();
                }
            }
        }
    }


}




