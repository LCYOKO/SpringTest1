package test;

import org.junit.jupiter.api.Test;
import test.juc.Mutex;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author l
 * @create 2020-05-24-11:10
 */
public class test01 {
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



    public  void initNum2(){
        for(int i=0;i<200;i++) num2[i]=0;
    }

    public  void num1AddNum2(int endIndex){
        for(int i=0;i<endIndex;i++){
            num1[i]+=num2[i];
        }
    }
    public  void getNumArray(int n){

        for(int i=1;i<=n;i++){
             initNum2();
             num2[0]=1;
             int endIndex=1;
            for(int j=2;j<=i;j++){
                int k=0;
                for(;k<endIndex;k++) num2[k]*=j;
                int c=0;
                k=0;
                while(c!=0||k<endIndex){
                    num2[k]+=c;
                    c=num2[k]/10;
                    num2[k]%=10;
                    k++;
                }
                endIndex=k;
            }
            num1AddNum2(endIndex);
        }
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        // write your code here

        Arrays.sort(nums);
        List<List<Integer>> ret=new ArrayList<>();
        int len=nums.length;
        for(int i=0;i<len-3;i++){
            if(i!=0&&nums[i]==nums[i=1]) continue;
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
}
