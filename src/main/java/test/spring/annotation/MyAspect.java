package test.spring.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author l
 * @Date 2021/2/21 11:54
 * @Version 1.0
 */
@Aspect
@Component
public class MyAspect {


    @Pointcut("execution(* test.spring.entity..*.say*(..))")
    private void pointcut(){

    }

    @Before("pointcut()")
    public  void doBefore(){
                 System.out.println("do before");
    }
}
