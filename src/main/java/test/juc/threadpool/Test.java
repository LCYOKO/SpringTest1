package test.juc.threadpool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author l
 * @Date 2021/1/16 15:27
 * @Version 1.0
 *
 *  核心参数
 *  coreSize，
 *  maxSize，
 *  blockingQueue，
 *  rejectHandler，
 *  非核心线程最大空闲时间，
 *  时间单位，
 *  ThreadFactory
 *
 *  -----------------------------------------------------------
 *  线程的异常是没法捕捉的，可以通过Thread.setDefaultUncaughtException 设置一个全局异常处理器
 *  或者调用为每个线程设置一个 ExceptionHandler
 *
 *  -----------------------------工作流程-----------------------
 *  1 先判断核心线程数是否打满，未满addWorker成功返回，否则获取线程池最新的状态
 *  2 判断线程是否正在运行并添加任务队列，成功再进行一次recheck
 *    2.1 recheck判断线程池状态不是运行的，则remove任务，并调用拒绝策略
 *    2.2 判断工作线程是否==0，如果=0说明核心线程数=0，需要添加一个非核心线程来处理
 *  3 任务队列满了，则添加非核心线程，把最大线程数打满
 *
 * -----------------------------线程复用的原理--------------------
 *  线程池对线程做了一个封装worker，并重写了run方法，run方法会调用runWorker
 *  runWorker是一个死循环while(task!=||(task=getTask()!=null)){}
 *  通过blockingQueue的take方法来实现阻塞，如果到了空闲时间最大值/线程池关闭都会导致task=null 进入finally，
 *  那么会进行入 finally方法 执行processWorkExist方法，
 *
 *  processWorkExist 方法会删除当前worker并记录当前线程执行的任务，并尝试关闭线程池tryTerminate
 *  如果当前线程是running或者当前线程是是shutdown&&任务队列非空直接返回，再判断当前线程数是否==0，非0
 *  直接interrupt打断所有空闲线程，返回。如果没有空闲线程，则把状态置为tiyding 执行terminate方法 再把状态置为erminate
 *
 *
 *  getTask方法也是一个死循环，如果线程池状态是shutdown&&任务队列非空|| 状态是stop 直接把workCount-1，并返回null
 *
 *
 *-----------------------------线程池关闭--------------------------
 * shutdown 方法是把线程状态置为shutDown，不允许添加任务并且会尝试打断未执行任务的线程
 * shutdown 方法会把线程池状态设置为stop，不允许添加任务并直接打断所有线程
 *
 *---------------------------优雅关闭线程池------------------------
 * 向JVM注册一个钩子方法，先调用shutdown方法，再等待一段时间后，直接调用shutdownNow。
 *
 *
 *
 *
 *
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //ExecutorService pool = Executors.newFixedThreadPool();
        //LongAdder adder = new LongAdder();
        // new AtomicStampedReference<>()
        //FutureTask<Object> Task = new FutureTask<>(() -> {
        //    Thread.sleep(5000);
        //    throw new RuntimeException();
        //});
        //Thread thread = new Thread(Task);
        //thread.start();
        //Task.get();
         Thread.setDefaultUncaughtExceptionHandler((t,e)->{
             System.out.println(e+"      ---------"+123);
         });
         Thread thread = new Thread();
        Thread.setDefaultUncaughtExceptionHandler(null);
        thread.setUncaughtExceptionHandler(null);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 30, 10, TimeUnit.MILLISECONDS
                , new ArrayBlockingQueue<Runnable>(10),
                new MyThreadFactory("xiaomi"),new MyRjectPolicy());
        threadPoolExecutor.execute(()->sayHi("123"));
        threadPoolExecutor.shutdown();


        threadPoolExecutor.shutdownNow();

        Future<?> submit = threadPoolExecutor.submit(() -> sayHi("456"));
    /*
    *  1单线程，避免了多线程竞争和上下文切换导致的时间浪费
    *  2基于内存操作，避免了无效的磁盘IO
    *  3IO多路复用，使得单线程的环境的下也能高效处理多个连接
    *  4高效的数据结构，如SDS
    *  5高效的网络协议，redis并没有使用先有的应用层协议，而是基于TCP重写了一个
    * ---------------------------------------------------------------------
    * SDS 简单动态字串串，支持O1时间复杂度获取字串串长度
    *                   支持动态扩容，当底层的数组长度不够时，会触发扩容，底层数组大小小于1M的时候成倍扩容，否则每次增加1M
    *                   惰性释放，当有字符被删除的时候，多余的空间不会立马被释放，而是用free字段来标记，方便下次使用
    *
    * zipList 压缩列表，普通的双向列表是用2个指针来指向前后节点，zipList没有使用指针，而是使用2个字段，preBytes来记录前一个node的
    * 大小，curBytes来记录当前的node的大小，这边查找前一个node只需要通过当前node的起始地址就+preBytes就行了，查找后一个node只需要
    * 当前节点的起始地址+curBytes. 压缩列表和数组一样内存紧凑
    *
    * dict 字典，字典中有一个长度为2的ht数组，0号元素指向正在使用的table，1号元素是扩容时候指向newTable的，而每个table和我们普通的
    * hashMap一样，有entryList,size,used,sizeMask等字段
    * 由于redis是单线程的，扩容没法一次性完成，所以每次扩容只会迁移一部分数据到newTable，每次search和delete操作都会查询2个table，
    * 但是新增只会插入到newTable，等扩容完毕后会，释放oldTable，然后oldTable指向newTable，newTable指向null。
    *
    * skipList 跳表是基于有序链表+多级索引实现的二分查询的，每一个节点通过的一个随机值来添加索引层数，每一次索引都是有序链表的一部分。
    *
    * 获取索引层数  while(random.getRandom()>p&&level<maxLevel) level++;
    * search操作  就是有序链表的search  while(head.next!=null && head.val<target) head=head.next
    *                                 if(head==null || head.val!=target) return search(head=head.next);
    *                                 return head.val;
    * insert操作也一样  先获取层数，然后每一层按照有序链表的insert操作
    * delete也一样，最层索引开始删除，然后每一层按照有序链表的delete操作就可以了
    * ------------------------------------------------------------------------------
    * redis的删除策略有2个   1）懒删除，每次get操作的时候判断是否过期，过期则删除
    *                       2）按照时间片删除，每次都会启动一个很短的定时任务，删除一部分数据
    *
    * redis的淘汰策略      对于设置过期时间的key  volatile-lru，volatile-random，volatile-ttl
    *                     对于所有键   allKeys-lru, allKeys-random
    * ------------------------------------------------------------------------------
    * redis的持久化  RDB和AOF
    *
    * RDB 是对当前内存生成的快照 进行持久化， 1)手动执行bgsave
    *                                      2)父进程fork 一个子进程，使用copyOnWrite技术拷贝一个内存快照
    *                                     3）子进程基于内存数据，生成一个二进制的rbd文件
    *                                     4）写完后通知父进程，父进程会把新生成的rdb文件替换旧的文件
    * 优点：rdb文件是二进制的，文件比较小，而且加载速度较快，所以一般用作主从同步的全量复制
    * 缺点：rdb是基于内存快照生成的一个全量文件，所以持久化的频率不好控制，频率高了rdb的持久化效率就低
    * 频率低了，数据的一致性就差
    *
    *
    * AOF 是把redis服务器执行完的命令追加写入aof文件， 1）父进程执行一个执行完后，把命令写入AOFBUFF
    *                                              2） 写入AOFBUFF后根据配置文件的刷盘策略进行刷盘
    *                                                                           awals 写入一次就刷盘
    *                                                                           everySeconds 每一秒刷盘
    *                                                                           直接写入内核缓存区，让操作系统来执行
    * 优点：执行效率较高和一致性较高，最多丢失1秒的数据
    * 缺点，数据冗余较多，2）由于保存的是数据，加载的时候需要进行命令重放效率较低
    *
    * aof-rewrite，当aof文件大小超过重写阈值的时候会进行一个重写过程  1）父进程 fork一个子进程
    *                                                            2）子进程持续扫描数据，并生成相应的set命令
    *                                                            3）在重写的过程，父进程会继续相应客户维护一个aofBuff和rewriteBuff，
    *                                                            4）子进程也会把rewriterBuff中的数据写入aofTemp文件
    *                                                            5）子进程操作完毕后会发一个命令给父进程，父进程会等aof最后一次刷盘后完成文件提换
    *
    * --------------------------------------------------------------------
    *缓存雪崩，大量的Key同一时间失效/或redis实例宕机，对于key的过期时间可以随机设置一个如果是redis实例宕机，可以开启限流熔断来保证服务基本可用，然后联系DBA凯苏重启实例
    *
    *缓存穿透，恶意请求redis和mysql不存在的数据，把请求全部打到mysql上， 可以对于不存在的key设置null值或者使用布隆过滤器
    *
    *缓存击穿，热点数据过期，导致请求打到数据库，对于热点数据不设置过期时间/或者使用分布式锁，只允许一个请求访问数据库
    *
    * --------------------------------------------------------------------
    *                    集群的几种形式
    * 主从，从节点通过 slave of 命令完成主从
    * 1 从节点会发送一个pingSyn  runId=?&offeset=-1 的命令
    * 2 主节点收改命令后会进行一个bgsave命令生成一个rdb文件发送给从服务器
    * 3 从收rdb文件后，执行flushDB后，加载rdb文件
    * 4 从加载的时候，主节点会把执行的命令发送给从节点，同时维护一个复制缓存区和一个masterOfferset来实现离线重连的增量复制
    * 5 判断从节点的offerset是否等在复制缓存冲区找到，能找到直接返会相应的数据，否则的直接 fullSyn命令直接全量同步
    *
    * 优点：主从备份，完成基本的读写分离
    * 缺点：没有高可用，发送故障的时候没法故障自动转移 2）没法实现动态扩容
    *
    *
    * 哨兵集群
    * 1）哨兵会监听所有的数据节点和其他哨兵节点
    * 2）每个哨兵会通过订阅主节点的频道 完成哨兵集群的建立
    * 3）当哨兵发现某个节点ping不后，就认为主节点主观下线，然后询问其他节点是否获取主观下线数
    * 4）如果主观下线数大于配置文件中的quronm，那么就认定主简单客观下线，哨兵集群会发起一次哨兵leader选举
    * 5）每个哨兵都会要求其他哨兵投票给自己，当某个哨兵的节点票数大于max(qurom，n/2+1),那么该哨兵就是leader
    * 6）选完leader后，由leader完成故障切换，
    * 7）对从节点列表先进行过滤，然后按照优先级降序，offerset降序，runId升序取第一个
    * 8）对选出来的节点执行slave of no one，然后通知其他节点，让他们完成主从同步
    *
    * 优点：实现了高可用，可以自动完成故障切换，
    * 缺点：没法完成动态扩容，而且选择的过程中可能会出现脑裂导致数据丢失
    *
    * 于master节点和 salve节点和sentinel处于不同的网络分区，使得sentinel没有能够心跳感知到master，
    * 所以通过选举的方式提升了一个salve为master，这样就存在了两个master，这样会导致客户端还在old master那里写入数据，新节点无法同步数据，
    * 当网络恢复后，sentinel会将old master降为salve，这时再从新master同步数据，这会导致大量数据丢失。
    *
    * 可以通过配置2个参数来拒绝写入，避免脑裂，master最少有多少个从节点，和数据同步最大延迟时间，
    *
    *
    * cluster 集群 是通过一致性hash实现的，每个主节点只负责管理slot，数据是和slot关联的
    * client 通过hash&16384得出相应的slot，然后请求相应的主节点。
    * 故障转移，
    * 1）和哨兵模式一样，先判断主观下线，然后统计主观下线票数，票数大于n/2+1,则判断主节点客观下线
    * 2）发一个广播下线给从节点
    * 3）从节点收到下线消息后，会发送一个广播消息给所有主节点节点，要求他们投票给自己
    * 4）主节点会广播一条ack给消息给所有的从节点，告诉从节点他投给了那个
    * 5）从节点统计自己的票数，如果票数大于n/2+1,那么改节点就升级我为主节点，完成slot迁移和主从切换。
    *
    * --------------------------------------------------------------------
    * redis变慢
    * 1）存在大key，String类型大于 20KB就是大key，其他类型元素个数超过5000个|| 500M就是大key
    * 2）内存已经满了，每次添加操作都需要执行内存淘汰策略，才能执行后续的操作
    * 3）fork操作比较耗时，
    *
    *
    * 僵尸进程 就是子进程结束后，父进程没有回收子进程的pcb块，导致子进程持有进程号，进程运行时间等字段
    *
    * 孤儿进程，是父进程提前退出，但是孤儿进程的是可以通过init进程完成回收
    *
    * */


        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();

    }

    private static void sayHi(String name) {
        String printStr = "【thread-name:" + Thread.currentThread().getName() + ",执行方式:" + name+"】";
          System.out.println(printStr);
          Queue<Integer> que=new LinkedList<>();
        throw new RuntimeException(printStr + ",我异常啦!哈哈哈!");

    }


    //private void closeThreadPoolGracefully(Executor executor){
    //    try {
    //        executor.shutdown();
    //        if(!executor.awaitTermination(SHUTDOWN_TIME, TimeUnit.SECONDS)) {
    //            logger.info("Executor did not terminate in the specified time.");
    //            List<Runnable> droppedTasks = executor.shutdownNow();
    //            logger.info("Executors was abruptly shut down." + droppedTasks.size() + " tasks will not be executed.");
    //        }
    //
    //        logger.info("scheduled executor service closed normally.");
    //    }  catch (InterruptedException e) {
    //        logger.error("interrupted exception error...");
    //    }
    //
    //}

    @org.junit.Test
    public void testCondition() throws InterruptedException {
        final  ReentrantLock lock=new ReentrantLock();
        Condition condition = lock.newCondition();

       Thread t1=  new Thread(()->{
            try {
                lock.lock();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        });
       t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }

}
