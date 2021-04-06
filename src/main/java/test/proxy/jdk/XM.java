package test.proxy.jdk;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.beans.ConstructorProperties;

/**
 * @author l
 * @create 2020-06-04-22:24
 */

public class XM implements User {
    private  String name;
    public XM(){
        name="defalt";
    }
    public  XM(String name){
        this.name=name;
    }
    @Override
    public void SyaHello() {
          System.out.println(name);
    }
}
