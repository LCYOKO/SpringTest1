package test.myBeanFactoryPostProcess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.lang.ref.Reference;
import java.util.*;

/**
 * @author l
 * @create 2020-05-23-9:26
 */



class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}

public class test {

    @Test
    public  void func7(){
        //List<? extends  Fruit> list=new ArrayList<>();

    }

    @Test
    public void func1(){
//        minWindow("ADOBECODEBANC","ABC");
//        checkInclusion("abcdxabcde","abcdeabcdx");
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        String str2 = new String("str") + new String("01");
        str2.intern();
        String str1 = "str01";

        System.out.println(str2 == str1);
        String s3="123";


//        String str3 = new String("str01");
//        str3.intern();
//        String str4 = "str01";
//        System.out.println(str3 == str4);

    }
    public String minWindow(String s, String t) {
        int minLen=Integer.MAX_VALUE;
        int left=0;
        int right=0;
        int start=0;
        int window[]=new int[128];
        int need[]=new int[128];
        int size=0;
        int count=0;
        for(int i=0;i<t.length();i++){
            char c=t.charAt(i);
            if(need[c]==0) size++;
            need[c]++;
        }
        while(right<s.length()){
            char c=s.charAt(right);
            right++;
            if(need[c]!=0){
                window[c]++;
                if(window[c]==need[c]){
                    count++;
                }
            }
            while(count==size){
                if(right-left<minLen){
                    minLen=right-left;
                    start=left;
                }
                left++;
                char d=s.charAt(left);
                if(need[d]!=0){
                    if(window[d]==need[d]){
                        count--;
                    }
                    window[d]--;
                }
            }
        }
        return minLen==Integer.MAX_VALUE?"":s.substring(start,start+minLen);
    }



    public boolean checkInclusion(String s1, String s2) {
        int need[]=new int[26];
        int window[]=new int[26];
        int size=0;
        for(int i=0;i<s1.length();i++){
            char c=s1.charAt(i);
            if(need[c-'a']==0) size++;
            need[c-'a']++;
        }
        int left=0;
        int right=0;
        int count=0;
        while(right<s2.length()){
            char c=s2.charAt(right);
            window[c-'a']++;
            if(window[c-'a']==need[c-'a']) count++;
            if(right-left+1==size){

                if(count==size) return true;
                char d=s2.charAt(left++);
                if(need[d-'a']==window[d-'a']) count--;
                window[d-'a']--;
            }
            right++;
        }
        return count==size;
    }
}
