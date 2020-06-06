package test.proxy;

import org.checkerframework.checker.units.qual.C;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import test.proxy.cglib.MyMethondIncepter;
import test.proxy.jdk.MyInvovationHanlder;
import test.proxy.jdk.User;
import test.proxy.jdk.XM;

import java.lang.reflect.Proxy;

/**
 * @author l
 * @create 2020-06-04-22:22
 */
public class test {
    public static void main(String[] args) {
//     User proxyInstance =  (User)Proxy.newProxyInstance(test.class.getClassLoader(),new Class[]{User.class},new MyInvovationHanlder(new XM("小米")));
//       proxyInstance.SyaHello();
        User user = MyMethondIncepter.getInstance();
        user.SyaHello();
        FactoryBean
        SqlSessionFactoryBean
    }
}
