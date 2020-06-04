package test.pojo;

import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import test.annotation.TestAnnotation;
import test.myBeanFactoryPostProcess.test;

/**
 * @author l
 * @create 2020-05-21-22:41
 */
@Data
@Component
public class Student implements BeanPostProcessor {
    private String name;
    private Integer age;

    public void sayHello(){
          System.out.println("haha");
    }
    public void sayHello1(){
        System.out.println("12312");
    }
    public void func1(){
        System.out.println("func1");
    }

}
