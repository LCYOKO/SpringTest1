package test.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Pointcut;


/**
 * @author l
 * @create 2020-05-23-18:08
 */
@Component
@Aspect
public class testAop {
    @Pointcut(value = "execution(* test.pojo..*.say*(..))")
    public void func(){

    }
    @Before("func()")
    public void doBefore(){
        System.out.println("-----------------Aop");
    }
}
