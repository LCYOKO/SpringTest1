package test.spring.entity;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @Author l
 * @Date 2021/1/5 12:06
 * @Version 1.0
 */
@Component

@Import({test.spring.entity.MyImportSelector.class})
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("lisi");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
