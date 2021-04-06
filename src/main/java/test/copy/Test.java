package test.copy;

import javafx.concurrent.Worker;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author l
 * @create 2020-12-28-10:50
 */

public class Test {

     public static void say(){
         System.out.println("hello");
     }

    public static void main(String[] args) {
        UserVo vo = new UserVo();
        User user = new User();
        user.setId(2);
        user.setName("321");
        user.setPassword("asdas");
        vo.setId(1);
        vo.setName("123");
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        UserVo user1=mapperFactory.getMapperFacade().map(user,UserVo.class);
        System.out.println(user);
        System.out.println(user1);

    }



    class Test1{
        {
            System.out.println("hello Test1");
        }
    }

    <T> void showMsg(T val){
         System.out.println(val);
    }


   @org.junit.Test
  public   void test1() throws Throwable {

        this.<String>showMsg("123");
      // ThreadLocalRandom localRandom = new ThreadLocalRandom();
              //new ThreadPoolExecutor();
   }
}
