package test.guava.baseUtils;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

/**
 * @author l
 * @create 2020-06-04-21:11
 */
public class test {

    @Test
    public  void func1(){
        Integer i=1240;
        Integer j=1240;
        String s1=null;
        String s2=new String("123");
        List<Integer> list = Ints.asList(1, 2, 3, 4);
        list=Lists.reverse(list);
        System.out.println(list);
    }
}
