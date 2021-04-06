package test.juc.lock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author l
 * @Date 2021/1/31 22:30
 * @Version 1.0
 */
public class Test {
  static   ReentrantLock lock = new ReentrantLock();

    /**
    * @Author: lcy
    * @Description //TODO
    * @Date 16:28 2021/3/11
     *
     *  jdk1.6对synchronized做了优化，针对长时间只有一个线程访问的情况是偏向锁，有多个线程访问但是没有竞争的情况使用轻量级锁
     *  最后竞争激烈的情况是synchronized
     *  使用锁的实现依赖对象头的markWord，主要存储了对象的基本数据hashcode，age，偏向标志，锁标志
     *  --------------------------------偏向锁的实现------------------------------------
     *  1 判断markWord是否可偏向，markWord中是否有hashcode，
     *  2 如果都不满足则进行轻量级加锁.满足的话使用cas进行把markWord中的值置为当前ThreadId
     *  3 cas成功继续执行代码快，再进入同步块的时候检查markWord的ThreadId
     *  4 cas失败进行偏向撤销，等锁owner线程执行到安全点的时候，在Owner线程的栈创建一个lockRecord，把markWord中的数据拷贝到record中
     *  最后lockRecord中的object指向markWord，markWord指向record。
     *
     * --------------------------------轻量级锁的实现------------------------------------
     *  1 在当前栈中创建一个lockRecord, 并把markWord中的数据进行备份
     *  2 把markWord中的数据通过cas置为执行lockRecord的指针，成功就同时更新record的object指针执行markWord，否则进行自旋
     *  3 每次进行入同步代码块的时候会进行一个检查markWord中是否执行record，并创建一个空的lockRecord指向markWord
     *  4 如果自旋超时则升级为重量级锁，等线程1进入savePoint，创建Mointor对象并把markWord内容拷贝到Mointor对象同时修改markWord执行Mointer
     *  5 继续执行
     *
     *  ---------------------------- -重量级锁的实现------------------------------------
     *  1 mointor对象有几个重要的属性，owner是共享变量，通过cas把Owner修改当前的线程ID则表示获取锁成功，否则进入自旋
     *  2 自旋失败把当前线程封装成一个node Add到阻塞队列尾部。
     *  3 当owner释放锁的时候，会把阻塞队列的头部唤醒。
     *
     * -------------------------------------Synchronized和Lock的区别-----------------------------------
     *  1） syn是jvm层面实现的关键字，lock是java实现的类
     *  2） syn只支持非公平锁，lock支持公平和非公平
     *  3） syn获取不到锁会直接阻塞挂起，而lock支持tryLock，获取不到直接返回
     *  4） syn阻塞后无法中断，lock可以
     *  5） syn获取到锁是无法感知的，lock可以通过isHeldCurrentThread
     *  6） syn不需要显示加锁和释放锁，编译器会自动同步块前面加mutexEntry指令和mutexExist
     *
     * ----------------------------------- AQS-----------------------------------------------------
     * 通过一个通过cas修改volatile变量，修改成功就代表获取锁成功
     * acquire方法
     *  1）先调用tryAcquire方法，成功直接返回，否则把线程封装成节点从队尾入队后调用acquireQueued
     *  2) acquireQueued方法 先判断当前节点的Pre 是否是头节点，是的话调用tryAcquire获取锁，成功的话设置的当前节点未头节点
     *  3）设置头节点失败，调用shouldPackAfterFailedAcquire。判断node.pre是否取消，取消了就把节点往前移动直到找到一个有效的
     *  节点然后return false，进入第三轮循环和之前一样的操作，但是这一轮的操作是为了修改node.status=signal，然后直接调用parkInterupt
     *  方法，阻塞线程
     *
     * release方法
     *  1）调用tryRelease方法，把state减少到0
     *  2）判断头节点！=null并且status==signal 则会继续调用unparkSuccessor方法
     *  3）从头节点开始找到第一个未取消的节点
     *
     *
     *
     *
     *
    */
    public static void main(String[] args) {

      System.out.println(args.hashCode());
      int arr[][]=new int[2][];
              Thread.yield();
       try{
           lock.lockInterruptibly();

       }catch (Exception e){

       }finally {
           lock.unlock();
       }



    }



}
