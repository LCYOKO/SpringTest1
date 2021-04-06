package test.pojo;

import java.io.Serializable;

/**
 * @author l
 * @create 2020-05-21-22:41
 */

public class Student implements Serializable,Cloneable {
    private String name;
    private Integer id;
    private  Teacher teacher;
    public void sayHello(){
          System.out.println("haha");
    }
    public void sayHello1(){
        System.out.println("12312");
    }
    public void func1(){
        System.out.println("func1");
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        //Student clone = (Student)super.clone();
        //clone.setTeacher(this.teacher.clone());
        //return clone;
        return null;
    }

}
