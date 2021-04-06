package test.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author l
 * @Date 2021/1/3 23:10
 * @Version 1.0
 */
@ComponentScan("test.spring")
@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
public class AppConfig {

    //@Bean(initMethod ="init",destroyMethod = "destroy1")
    //public User   getUser(){
    //    return new User();
    //}



}
