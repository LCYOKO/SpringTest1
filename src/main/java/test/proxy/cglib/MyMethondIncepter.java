package test.proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import test.proxy.jdk.User;
import test.proxy.jdk.XM;

import java.lang.reflect.Method;

/**
 * @author l
 * @create 2020-06-06-17:03
 */
public class MyMethondIncepter implements MethodInterceptor {

    public  static User getInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(XM.class);
        enhancer.setCallback(new MyMethondIncepter());
        //enhancer.setCallbacks(null);
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
