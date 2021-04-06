package test.excle;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author l
 * @Date 2021/3/3 14:58
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String fileName="D:\\360Downloads\\5583190903036250910.xlsx";
        XSSFWorkbook sheets = new XSSFWorkbook(new FileInputStream(new File(fileName)));
        XSSFSheet sheetAt = sheets.getSheetAt(0);
         System.out.println(sheetAt.getRow(1).getCell(0));
         int cnt=0;
         String sql="delete * from bms_settlement_logistics_item where bsli_package_id in(";
         StringBuilder sb=new StringBuilder(sql);
         for(int i=1;i<=sheetAt.getLastRowNum();i++){
             sb.append("'"+sheetAt.getRow(i).getCell(0).getStringCellValue()+"',");
             System.out.println(sb);
         }
        File file = new File("D:\\360Downloads\\bmsOut.xlsx");
         if(!file.exists()){
             file.createNewFile();
         }
        XSSFWorkbook sheets1 = new XSSFWorkbook();
        XSSFSheet sheet = sheets1.createSheet("金额");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue(sb.toString());
        sheets1.write(new FileOutputStream(file));
    }

   @org.junit.Test
   public void test1(){
        String s1="abc";
        String s2="abe";
        int n=s1.length(),m=s2.length();
        int dp[][]=new int[n+1][m+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int flag=s1.charAt(i)==s2.charAt(j)?0:1;
                dp[i+1][j+1]=Math.min(Math.min(dp[i][j+1]+1,dp[i+1][j]+1),dp[i][j]+flag);
            }
        }
        System.out.println(dp[n][m]);
   }

   static class RandomizedSet {
        int arr[];
        int lastIndex;
        Map<Integer,Integer> map;
        Random random;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            arr=new int[20];
            lastIndex=-1;
            random=new Random();
            map=new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val)){
                return false;
            }
            map.put(val,lastIndex+1);
            arr[++lastIndex]=val;
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            // System.out.println(Arrays.toString(arr));
            //   System.out.println(map);
            if(!map.containsKey(val)){
                return false;
            }
            if(lastIndex==0 || map.get(val)==lastIndex){
                map.remove(val);
            }
            else{
                int idx=map.get(val);
                map.put(arr[lastIndex],idx);
                //   System.out.println(map.);
                swap(idx,lastIndex);
                map.remove(val);
            }
            //    System.out.println(map);
            lastIndex--;
            return true;
        }
        private void swap(int i,int j){
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int idx=random.nextInt(lastIndex+1);
            return arr[idx];
        }
    }
    @org.junit.Test
    public void test2(){
        Node head=build();
        //printNode(head);
        Node dump=new Node(-1);
        Node p=dump;
        p.next=head;
        while(head!=null){
            // 1,2,2,3,4,5,5
            while(head.next!=null && head.val==head.next.val) head=head.next;
            if(p.next!=head){
                p.next=head;
            }
            p=p.next;
            head=head.next;
        }
        printNode(dump.next);
    }

    public void printNode(Node node) {
        while(node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }
    public Node build(){
        int arr[]={1,2,2,3,4,5,5};
        int idx=0;
        Node head=new Node(-1);
        Node p=head;
        while(idx<arr.length){
            head.next=new Node(arr[idx++]);
            head=head.next;
        }
        return p.next;
    }
    class Node{
        Node next;
        int val;
        Node(int val){
            this.val=val;
        }
    }


//   biz业务表.biz_recive, biz_relation


     @org.junit.Test
    public  void testRed(){
        Random random=new Random();
        // dp[

         final ReentrantLock lock = new ReentrantLock();

         int divde=10;
        getArray(10*divde,10,random);
       // System.out.println(divideRedPackage(100,10));
    }

    public void getArray(int amount,int num,Random random){
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<num-1;i++){
            int cur=random.nextInt(amount/(num-i)*2)+1;
            ans.add(cur);
            amount-=cur;
        }
        ans.add(amount);
        System.out.println(ans);
        scatterArray(ans,random);
        System.out.println(ans);
    }

    private void scatterArray(List<Integer> l,Random r){
        int n=l.size();
        for(int i=0;i<n;i++){
            int idx=r.nextInt(n-i);
            System.out.println(idx);
            swap(l,idx,n-i-1);
        }
    }
    private void swap(List<Integer> l,int i,int j){
        if(i>=j) {
            return;
        }
        int temp=l.get(i);
        l.set(i,l.get(j));
        l.set(j,temp);

    }

    private static List<Integer> divideRedPackage(int totalAmount, int totalPeopleNum) {
        List<Integer> amountList = new ArrayList<>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;

        Random random = new Random();
        for (int i= 0; i<totalPeopleNum- 1; i++){
            //随机范围：[1，剩余人均金额的两倍)，左闭右开
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum --;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }
    @org.junit.Test
    public void test4(){
      List<Integer> l=new ArrayList<>();
      Scanner sc=new Scanner(System.in);
    }

    @org.junit.Test
    public void test3() throws InterruptedException {

//   第一步创建 20个账户
//   第二步初始化2个random和一个latch;
//   第三步new10个线程
        List<Account> list=build1();
        CountDownLatch latch= new CountDownLatch(1000);


//       具体实现逻辑就是分段的思想，每次获取一个out账户并且尝试获取锁，如果成功再判断当前账户钱是否大于随机转账值
//       成立则往下走，获取一个in账户，判断是否和之前的那个账号一样，如果一样则跳过，反之进行获取锁的操作，然后进行
//       转账的业务逻辑处理。
        for(int i=0;i<10;i++){
            new Thread(()->{
                int cnt=0;
                while(cnt<100){
                    Account out=null;
                    Account in=null;
                    try{
                        ThreadLocalRandom random = ThreadLocalRandom.current();
                        int amount=random.nextInt(100)+1;
                         out=list.get(random.nextInt(20));
                        if (!out.tryLock() || out.amount < amount) {
                            continue;
                        }
                         in=list.get(random.nextInt(20));
                        if(in.id==out.id || !in.tryLock()) {
                            continue;
                        }
                        out.dec(amount);
                        in.inc(amount);
                        cnt++;
                        latch.countDown();
                    }catch(Exception e){

                    }
                    finally{
                        if(out!=null){
                            out.unlock();
                        }
                        if(in!=null){
                            in.unlock();
                        }

                    }
                }

            }).start();
        }
        latch.await();
        printList(list);

    }


    public void printList(List<Account> l){
        int sum=0;
        for(Account account:l){
            sum+=account.amount;
            System.out.println(account.id+"拥有----"+account.amount);
        }
        System.out.println(sum);
    }

    public static List<Account> build1(){
        List<Account> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add(new Account(i));
        }
        return list;

    }


    //    每个account类中有一个lock，id，amount
    static class Account {

        int amount;
        int id;
        ReentrantLock lock;
        Account(int id){
            this.amount=10000;
            this.id=id;
            lock=new ReentrantLock();
        }

        public boolean tryLock(){
            return lock.tryLock();
        }

        public void unlock(){
            if(lock.isHeldByCurrentThread())lock.unlock();
        }

        public void inc(int val){
            this.amount+=val;
        }

        public void dec(int val){
            this.amount-=val;
        }
    }
     @org.junit.Test
    public void test5() throws InterruptedException {
     Object lock=new Object();
     Thread arr[]=new Thread[10];
     for(int i=0;i<10;i++){
         Runnable target;
         arr[i]=new Thread(()->{
             try {
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName());
                }

             } catch (Exception e) {
                 e.printStackTrace();
             }
         },i+"");
     }
         synchronized (lock){
         for(int i=0;i<10;i++){
             arr[i].start();
             Thread.sleep(1000);
         }
     }

    }


    // serviceA(核心接口)  ------------->  serviceB(重要非核心)
    public void printArray(int arr[],int i,int j){
        if(i>j){
            return;
        }
        while(i<=j){
            System.out.println(arr[i++]);
        }

    }




}
