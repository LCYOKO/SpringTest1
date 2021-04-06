package test;

import org.junit.Test;
import test.spi.SearchService;

import java.util.*;

/**
 * @author l
 * @create 2020-08-26-22:52
 */

public class test02 {
    Object lock = new Object();

    public void startThreadA(){
        new Thread(() -> {
            synchronized (lock){
                log("get lock");
                startThreadB();
                log("start wait");
                try {
                    lock.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                log("get lock after wait");
                log("release lock");
            }
        }, "thread-A").start();
    }

    public void startThreadB(){
        new Thread(()->{
            synchronized (lock){
                log("get lock");
                startThreadC();
               // sleep(100);
                log("start notify");
                lock.notify();
                log("release lock");

            }
        },"thread-B").start();
    }
    public void log(String msg){
        System.out.println(Thread.currentThread().getName()+msg);
    }
    public void startThreadC(){
        new Thread(() -> {
            synchronized (lock){
                log("get lock");
                log("release lock");
            }
        }, "thread-C").start();
    }


    @Test
   public  void test01(){
         startThreadA();
   }

   @Test
    public  void test02() throws InterruptedException {
       ServiceLoader<SearchService> s = ServiceLoader.load(SearchService.class);
       Iterator<SearchService> iterator = s.iterator();
       while (iterator.hasNext()) {
           SearchService search =  iterator.next();
           search.getAllFiles("hello world");
       }
   }

   @Test
   public void  test03(){
        int nums[]={4,3,2,7,8,2,3,1};
       List<Integer> ans=new ArrayList<>();
       int n=nums.length;
       for(int i=0;i<n;){
           if(nums[i]==0||nums[nums[i]-1]==i+1){
               i++;
               continue;
           }
           if(nums[nums[i]-1]!=nums[i]){
               swap(nums[i]-1,i,nums);

           }
           else{
               ans.add(nums[i]);
               nums[i]=0;
               i++;
           }
           System.out.println(Arrays.toString(nums)+" "+i);
       }
   }
    private void swap(int i,int j,int nums[]){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }

    @Test
    public void test1(){
        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("bad");
        dictionary.search(".ad");

    }


    class WordDictionary {
        Node head;
        /** Initialize your data structure here. */
        public WordDictionary() {
            head=new Node();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            Node p=head;
            for(int i=0;i<word.length();i++){
                char c=word.charAt(i);
                if(p.child[c-'a']==null) p.child[c-'a']=new Node();
                p=p.child[c-'a'];
            }
            p.isEnd=true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            Node p=head;
            for(int i=0;i<word.length();i++){
                char c=word.charAt(i);
                if(c!='.'&&p.child[c-'a']==null) return false;
                if(c=='.'){
                    boolean res=false;
                    String subString=word.substring(i+1,word.length());
                    for(Node node:p.child){
                        if(node==null) continue;
                        res=search(subString,node);
                        if(res) return true;
                    }
                    return res;
                }
                p=p.child[c-'a'];
            }
            return p.isEnd;
        }
        public boolean search(String word,Node p){
            for(int i=0;i<word.length();i++){
                char c=word.charAt(i);
                if(c!='.'&&p.child[c-'a']==null) return false;
                if(c=='.'){
                    boolean res=false;
                    String subString=word.substring(i,word.length());
                    for(Node node:p.child){
                        if(node==null) continue;
                        res|=search(subString,node);
                    }
                    return res;
                }
                p=p.child[c-'a'];
            }
            return p.isEnd;
        }

        class Node{
            Node child[];
            boolean isEnd;
            public Node(){
                child=new Node[26];
            }
        }
    }
}
