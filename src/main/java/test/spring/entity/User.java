package test.spring.entity;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author l
 * @Date 2021/1/5 10:26
 * @Version 1.0
 */
@Data
@Component
public class User implements InitializingBean, DisposableBean, Ordered {
     private String name;
     private Integer id;


    public User (){
        System.out.println("construct");
    }
    @PostConstruct
    public void postConstruct(){
       System.out.println("postConstruct---user");
    }



   @PreDestroy
   public void preDestroy(){
        System.out.println("preDestroy");
   }

    public  void destroy1(){
        System.out.println("haha");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
          System.out.println("afterPropertiestSet---user");
    }

    private void init() {
        System.out.println("init");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("interface destroy");
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
