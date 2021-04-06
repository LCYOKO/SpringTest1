package test.proxy;

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
        User proxyInstance = (User) Proxy.newProxyInstance(test.class.getClassLoader(),
                new Class[]{User.class},
                new MyInvovationHanlder(new XM("11")));
        proxyInstance.hashCode();
        proxyInstance.toString();
        proxyInstance.equals(null);
        User user = MyMethondIncepter.getInstance();
        System.out.println(	42*153);
        //user.SyaHello();
        //user.hashCode();
        //user.toString();
        //user.equals(null);
       System.out.println(user);
//       proxyInstance.SyaHello();
//        User user = MyMethondIncepter.getInstance();
//        user.SyaHello();



    }
    static boolean boolValue;


    public int maxProfit(int k, int[] prices) {
        int n=prices.length;
        k=Math.min(k,n/2);
        k*=2;
        if(n==0) return 0;
        int dp[][]=new int[n+1][k+1];
        for(int i=1;i<=k;i+=2){
            dp[0][i]=-prices[0];
        }
        for(int i=0;i<n;i++){
            for(int j=1;j<=k;j++){
                if(j%2==1){
                    dp[i+1][k]=Math.max(dp[i][k-1]-prices[i],dp[i][k]);
                }
                else {
                    dp[i+1][k]=Math.max(dp[i][k-1]+prices[i],dp[i][k]);
                }
                System.out.println(dp[i+1][k]);
            }
        }
        return dp[n][k];

    }
}
