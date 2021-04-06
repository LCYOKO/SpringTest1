package test.spring.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @Author l
 * @Date 2021/1/3 23:11
 * @Version 1.0
 */
@Service
public class UserService implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        //System.out.println("do somethings after！！！！");
    }
}
