package test.jvm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author l
 * @create 2020-12-22-10:50
 */
public class TestGc {
    static double sum = 0;


    @Test
    public  void test(){
         List<String> list=new ArrayList<>();
         list.add("why");
         list.add("why1");
         list.add("倒数第二个");
         list.add("倒数第一个");
         System.out.println(list);
         int i=0;
         for(String s:list){
             System.out.println(s);
             if("倒数第二个".equals(s)){
                 list.remove(s);
             }

         }
         System.out.println(list);
    }
    public static void foo() {
        for (int i = 0; i < 0x77777777; i++) {
            sum += Math.sqrt(i);
        }
    }

    public static void bar() {
        for (int i = 0; i < 50_000_000; i++) {
            new Object().hashCode();
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.stream();
        new Thread(TestGc::foo).start();
        new Thread(TestGc::bar).start();
    }
}
