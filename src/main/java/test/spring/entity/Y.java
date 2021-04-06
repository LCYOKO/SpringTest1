package test.spring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author l
 * @Date 2021/2/21 18:19
 * @Version 1.0
 */
@Component
public class Y {
    @Autowired
    private X x;

    public void sayHi(){
        System.out.println("sayHi");
    }
    public void sayHi1(){
        System.out.println("sayHi1");
    }
    public X getX(){
        return  x;
    }
}
