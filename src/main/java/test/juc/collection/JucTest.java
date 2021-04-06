package test.juc.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * @Author l
 * @Date 2021/3/13 15:58
 * @Version 1.0
 * ------------------------------------线程安全的容器---------------------------------
 * 1）线程安全的容器分为2大类，同步容器和并发容器
 * 同步容器：直接在方法上面添加synchronized关键字，效率较低
 * 并发容器：juc包下的各种容器，比如copyOnWriteList，concurrentHashMap,ArrayBlockingQueue等
 * --------------------------------------BlockQueue-----------------------------------
 * 1 ArrayBlockingQueue 入队和出队都公用一把锁，队列满的时候put方法会阻塞，队列为空的时候
 * 2 LinkedBlockingQueue 入队和出队的分开锁定，减少锁的粒度
 * 3 SynchronousQueue 无缓冲队列，当put方法调用后，没有线程调用take方法，则后续的put方法都会阻塞
 * 4 DelayQueue 基于优先级队列来实现的延时队列，当获取元素的时候通过getDelay获取延时，如果值<=0直接返回，否则调用park方法
 *--------------------------------------CopyOnWriteList------------------------------
 * 1 用快照来实现非阻塞并发，没次写都会拷贝一份快照，然后再快照上修改数据
 * 2 缺点很明显，写操作内存消耗较大，而且可能读到脏数据
 *-------------------------------------ConcurrentHashMap 1.7和1.8的区别-----------------------------
 * 1 1.7使用了分段的思想，把hashMap分为多个segment，每个segment都继承ReentrantLock,这样就保证了并发安全
 * 2 1.8对继续做了优化，1）减少锁粒度 2）减少锁时间）3）使用了CAS+Synchronized
 *-------------------------------------ConcurrentHashMap-------------------------------------------
 * put方法
 *    1 判断key或val是否为null，是的话直接抛出异常
 *    2 进入一个死循环
 *      2.1 判断table是否初始化，没有初始化则初始化,调用init方法
 *      2.2 通过hash得到index，判断头结点是否==null，未null的话直接进行cas设置头结点，成功break，否则进入下轮循环
 *      2.3 判断头结点是否正在扩容，是的话调用 helpTransfer方法()，协助扩容
 *      2.4 锁住头结点，和hashMap代码一样
 *   3 调用addCount方法，进行元素+1和扩容判断操作
 * init方法
 *    1 判断table是否为null，如果不是的话直接返回
 *    2 判断sizeCtl==-1，如果是的话表明容器正在初始化，执行yield方法让出时间片
 *    3 通过cas设置sizeCtl=-1，表明正在初始化
 *
 * addCount方法
 *   1 现cas进行，baseCount+1，如果成功则直接入扩容判断逻辑
 *   2 如果baseCount设置设备，根据hash选择一个cellCount进行cas+1
 *
 * helpTransfer方法
 *     1判断table!=null&&头结点==ForWardingNode&&node.nextTable!=null 才会执行transfer方法
 * size方法
 *    直接baseCount加上所有的countCell
 *
 * remove方法
 *     和put方法一样
 *
 *
 */
public class JucTest {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        SynchronousQueue<Integer> queue1 = new SynchronousQueue<>();


    }
}
