package test.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import test.proxy.jdk.User;
import test.proxy.jdk.XM;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author l
 * @create 2020-06-06-17:03
 */
public class MyMethondIncepter implements MethodInterceptor {

    public  static User getInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(XM.class);
        enhancer.setCallback(new MyMethondIncepter());
        return   (User) enhancer.create();

    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
         System.out.println("cglib do before");
        Object o1 = methodProxy.invokeSuper(o, objects);
         System.out.println("cglib do after");
        return o1;
    }
}
