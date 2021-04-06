package test.proxy.jdk;

import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author l
 * @create 2020-06-04-22:31
 */
@Data
public class MyInvovationHanlder<T> implements InvocationHandler {
     private T target;
     public MyInvovationHanlder(T user){
         target=user;
     }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         System.out.println("do before");
         Object o = method.invoke(target, args);
         System.out.println("do after");
        return o;
    }
}
