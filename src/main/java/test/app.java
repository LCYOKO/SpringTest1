package test; /**
 * @author l
 * @create 2020-05-21-22:38
 */
import org.springframework.aop.framework.AopProxy;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.pojo.Student;
import test.pojo.Teacher;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class app {
    public static int num1[]=new int[200];
    public  static int num2[]=new int[200];
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
//
//
////
////        System.out.println(student);
//        Student bean = context.getBean(Student.class);
//        bean.sayHello();
//        bean.sayHello1();
//        bean.func1();
        //System.out.println();
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        getNumArray(n);
        //System.out.println(Arrays.toString(num1));
        printNum1();

    }
    public  static void printNum1(){
      int endIndex=0;
      int c=0;
      while(num1[endIndex]!=0||c!=0){
          num1[endIndex]+=c;
          c=num1[endIndex]/10;
          num1[endIndex]%=10;
          endIndex++;
      }
      if(endIndex==0) return;

      for(int i=endIndex-1;i>=0;i--) System.out.print(num1[i]);
    }

    public  static void initNum2(){
        for(int i=0;i<200;i++) num2[i]=0;
    }

    public static void num1AddNum2(int endIndex){
        for(int i=0;i<endIndex;i++){
            num1[i]+=num2[i];
        }
    }
    public static void getNumArray(int n){

        for(int i=1;i<=n;i++){
            initNum2();
            num2[0]=1;
            int endIndex=1;
            for(int j=2;j<=i;j++){
                int k=0;
                for(;k<endIndex;k++) num2[k]*=j;
                int c=0;
                k=0;
                while(c!=0||k<endIndex){
                    num2[k]+=c;
                    c=num2[k]/10;
                    num2[k]%=10;
                    k++;
                }
                endIndex=k;
            }
            num1AddNum2(endIndex);
            //System.out.println(Arrays.toString(num2));
        }
    }

}
