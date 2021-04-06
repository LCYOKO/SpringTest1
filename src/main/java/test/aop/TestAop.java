package test.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author l
 * @create 2020-05-23-18:08
 */
@Component
@Aspect
public class TestAop {
    //value = "execution(* test.pojo..*.say*(..))"
    LinkedHashMap<Integer, Integer> map;
    @Pointcut("@annoation()")
    public void func(){
      //  int cacheSize=10;
      int  cacheSize = 10;
     map = new LinkedHashMap<Integer,Integer>(16,0.75F,true){

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if(cacheSize + 1 == map.size()){
                    return true;
                }else{
                    return false;
                }
            }
        };


    }
    @Before(value = "fuc")
    public void doBefore(){
        System.out.println("-----------------Aop");
    }
}
