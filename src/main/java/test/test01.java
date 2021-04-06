package test;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import test.juc.lock.Mutex;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author l
 * @create 2020-05-24-11:10
 */
public class test01 {


     @Test
     public void test15(){
         String path="D:\\360Downloads\\";
         String name="zeus_2020_9_9 (2).csv";
         String ENCODE="UTF-8";
         try (CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(new File(path+name)), ENCODE))).build()) {
             File file = new File("src/main/resources/中心.txt");
             FileWriter vFileWriter = new FileWriter(file);
             BufferedWriter vBufferedWriter = new BufferedWriter(vFileWriter);
             Iterator<String[]> iterator = csvReader.iterator();
             String temp[];
             String insert="insert into bms_original_package ";
             boolean isFirst=false;
             while (iterator.hasNext()) {
                 temp=iterator.next();
                 StringBuilder builder = new StringBuilder();
                 builder.append('(');
                 if(isFirst) {
                      for (int i = 1; i < temp.length; i++){
                          builder.append(temp[i]);
                          if(i!=temp.length-1) builder.append(',');
                      }
                  }
                  else{
                     for (int i = 1; i < temp.length; i++){
                         builder.append(temp[i]);
                         if(i!=temp.length-1) builder.append(',');
                     }
                  }
                  builder.append(')');
                  System.out.println(builder);
                  vBufferedWriter.write(builder.toString());
                  vBufferedWriter.write("\n");
                  vBufferedWriter.flush();
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }



    @Test
    public  void test14(){
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionTimeToLive(6000, TimeUnit.MILLISECONDS).build();


    }

    @Test
    public  void jucTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
//        Student student = new Student();
//        new Thread(()->{
//            student.setId(1);
//        });
//        String s = new String("1");
//        s=s.intern();
//        String s2 = "1";
//
//        System.out.println(s == s2);
//
//        String s3 = new String("1") + new String("1");
//        s3.intern();
//        String s4 = "11";
//        System.out.println(s3 == s4);
//           String s="cc";
//        String h = new String("cc");
//         h.intern();
//         h.intern();
//        System.out.println(h.intern() == h);
//        FileInputStream stream = new FileInputStream();

        String str2 = new String("str") ;
        str2.intern();
        String str1 = "str01";
        str2.intern();
        System.out.println(str2 == str1);

        String str3 = new String("str01");
        str3.intern();
        String str4 = "str01";
        System.out.println(str3 == str4);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(null);
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
//        ArrayList<Integer> arrayList3=new ArrayList<Integer>();
//        arrayList3.add(1);//这样调用add方法只能存储整形，因为泛型类型的实例为Integer
//        arrayList3.getClass().getMethod("add", Object.class).invoke(arrayList3, "asd");
//        for (int i=0;i<arrayList3.size();i++) {
//
//            System.out.println(arrayList3.get(i));
//        }
//
//        ArrayList<? extends Number> list=new ArrayList<>();
//        ArrayList<? super >


    }

    @Test
    public  void test13() throws IOException, ClassNotFoundException, CloneNotSupportedException {
        String prefix="src/main/resources";
//        InputStream in = new FileInputStream(prefix+"a.txt");
//        ObjectInputStream os = new ObjectInputStream(in);
//       Student s= (Student) os.readObject();
//       System.out.println(s);
//         OutputStream os=      new FileOutputStream(prefix+"a.txt");
//        ObjectOutputStream stream = new ObjectOutputStream(os);
//        Student student = new Student();
//        student.setId(1);
//        student.setName("123");
//        Teacher teacher = new Teacher();
//        teacher.setName("lisi");
//        student.setTeacher(teacher);
//        Student student1 = student.clone();
//        student.getTeacher().setName("wangwu");
//        System.out.println(student1);

//        stream.writeObject(student);
//           os.close();
    }

//    @Test
//    public  void test5(){
////        MyQueue<Integer> queue = new MyQueue<>(2);
////        System.out.println(queue.isEmpty());
////        System.out.println(queue.push(1));
////        System.out.println(queue.push(2));
////        System.out.println(queue);
////        System.out.println(queue.size());
////        System.out.println(queue.isFull());
////        System.out.println(queue.pop());
////        System.out.println(queue.pop());
////        System.out.println(queue.isEmpty());
//        Student student = new Student();
//        Student student1 = new Student();
//        student.setName("123");
//        student.setId(1);
//
//        HashSet<Student> set = new HashSet<>();
//        set.add(student);
//        //set.add(student1);
//        student.setId(3);
//        set.add(student);
//        for (Student s:set
//             ) {
//            System.out.println(s.hashCode());
//        }
//        System.out.println(set);
//
//    }



     @Test
     public   void test4() {
         String surfix=".txt";
         String fileName = "src/main/resources/中心";
         try {
             File file = new File(fileName);
             File fileOut=new File(fileName+"out"+surfix);
             if(!fileOut.exists()) file.createNewFile();
             InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
             BufferedReader bf = new BufferedReader(inputReader);

            //System.out.println(file.exists());
             FileWriter vFileWriter = new FileWriter(fileOut);
             BufferedWriter vBufferedWriter = new BufferedWriter(vFileWriter);
             String str;
             int cnt=0;
             while ((str = bf.readLine()) != null) {
              cnt++;
              vBufferedWriter.write(str);
              vBufferedWriter.newLine();
              if(cnt==10){
                  cnt=0;
                  vBufferedWriter.write("======================================");
                  vBufferedWriter.newLine();
              }
             }
             if(cnt!=0&&str!=null&&str.length()>0){
             vBufferedWriter.write(str);
             vBufferedWriter.newLine();}
             vBufferedWriter.flush();
             bf.close();
             inputReader.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

         @Test
     public  void test3() throws ExecutionException, InterruptedException {
         FutureTask<Integer> task = new FutureTask<>(()->{
                Thread.sleep(3000);
             return 1024;
         });
                    new Thread(task,"thread-01").start();
             System.out.println(1111);
             System.out.println(task.get());
             System.out.println(2222);
     }
    @Test
    public  void test(){

        String s="#";
        String s1="2";

        System.out.println(Arrays.toString(s.split("#")));
    }
    public int compress(char[] chars) {
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor) {
                    for (char c: ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;

    }

    public int[] masterMind(String solution, String guess) {
        int ret[]=new int[2];
        int table[]=new int[26];
        int table1[]=new int[26];
        for(int i=0;i<solution.length();i++){
            table[solution.charAt(i)-'A']++;
            table1[guess.charAt(i)-'A']++;
        }
        int sum1=0;
        int sum2=0;
        for(int i=0;i<guess.length();i++){
            if(guess.charAt(i)==solution.charAt(i)) sum1++;
            //System.out.println(Math.min(table1[guess.charAt(i)-'A'],table[solution.charAt(i)-'A']));
            //System.out.println(table1[solution.charAt(i)-'A']+"   "+table[solution.charAt(i)-'A'])
        }
        for(int i=0;i<26;i++) sum2+=Math.min(table[i],table1[i]);
        ret[0]=sum1;
        ret[1]=sum2-sum1;
        return ret;
    }

    public void rotate(int[] nums, int k) {
        int n=nums.length;
        k=k%n;
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);
        reverse(nums,0,n-1);
    }
    void reverse(int nums[],int i,int j){
        while(i<j){
            int temp=nums[i];
            nums[i++]=nums[j];
            nums[j--]=temp;
        }
    }
   public  int num1[]=new int[200];
   public  int num2[]=new int[200];
    @Test
    public  void func3(){


//      int arr[]={1,2,3,4,5,6,7,8};
//      rotate(arr,3);
//
////        HashSet<Integer> set = new HashSet<Integer>;
//        int a='A';
//        Queue<Integer> que=new LinkedList<>();
//        System.out.println(a);
//        System.out.println(Arrays.toString(arr));

//         Queue<Integer> que=new LinkedList<>();
//          que.pee
//        int k=4;
//        String s="2-4A0r7-4k";
//        s=s.toUpperCase();
//        int len=s.length();
//        int cnt1=0;
//        StringBuilder builder = new StringBuilder();
//        for(int i=0;i<len;i++){
//            if(s.charAt(i)=='-') cnt1++;
//        }
//        int s1=(len-cnt1)%k==0?k:(len-cnt1)%k;
//
//         cnt1=(len-cnt1)%k==0?(len-cnt1)/k-1:(len-cnt1)/k;
//         System.out.println(cnt1);
//        int i=0;
//        for(;i<len&&s1>0;i++){
//            if(s.charAt(i)=='-') continue;
//            s1--;
//            builder.append(s.charAt(i));
//        }
//        if(cnt1>0) builder.append('-');
//        int t=0;
//        for(;i<len&&cnt1>0;i++){
//            if(s.charAt(i)=='-'){
//
//                continue;
//            }
//            builder.append(s.charAt(i));
//            t++;
//           if(t==k){
//             t=0;
//             cnt1--;
//             if(cnt1>0) builder.append('-');
//           }
//        }
//        System.out.println(builder);


          int arr[]={1,0,-1,0,-2,2};
          fourSum(arr,-2);

        }






    public List<List<Integer>> fourSum(int[] nums, int target) {
        // write your code here

        Arrays.sort(nums);
        List<List<Integer>> ret=new ArrayList<>();
        int len=nums.length;
        for(int i=0;i<len-3;i++){
            if(i!=0&&nums[i]==nums[i-1]) continue;
            for(int j=i+1;j<len-2;j++){
                if(j!=i+1&&nums[j]==nums[j-1]) continue;
                int l=j+1;
                int r=len-1;
                while(l<r){
                    if(r!=len-1&&nums[r+1]==nums[r]){
                        r--;
                        continue;
                    }
                    int sum=nums[i]+nums[j]+nums[l]+nums[r];
                    System.out.println(sum>target);
                    if(sum==target){
                        List<Integer> list=new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        ret.add(list);
                        l++;
                        r--;
                    }
                    else if(sum>target) r--;
                    else l++;

                }
            }
        }
        return ret;
    }

    static int count=0;
   @Test
    public  void func4() throws InterruptedException {
       ReentrantLock lock = new ReentrantLock();
       Condition condition = lock.newCondition();
       condition.await();
       Mutex mutex = new Mutex();
       for(int i=0;i<10;i++){
           new Thread(()->{
               mutex.lock();
               for(int j=0;j<10000;j++){
                   count++;
               }
               mutex.unlock();
           }).start();
       }

       Thread.sleep(2000);
       System.out.println(count);

   }

    @Test
    public  void func5(){
       int nums[]={0,3,7,2,5,8,4,6,0,1};
       longestConsecutive(nums);
    }
    public int longestConsecutive(int[] nums) {
        int max=1;
        if(nums.length==0) return 0;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],1);
                if(map.containsKey(nums[i]-1)){
                    max=Math.max(max,merge(map,nums[i]-1,nums[i]));
                }
                if(map.containsKey(nums[i]+1)){
                    max=Math.max(max,merge(map,nums[i],nums[i]+1));
                }
            }

        }
        return max;
    }

    private int merge(Map<Integer,Integer> map,int less,int more){
        int left=less-map.get(less)+1;
        int right=more+map.get(more)-1;
        int len=right-left+1;
        map.put(less,len);
        map.put(more,len);

        return len;
    }
}
