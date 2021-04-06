package test.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author l
 * @Date 2021/2/23 10:59
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();

    }


    private static  List<Order> build(){
        List<Order> l=new ArrayList<>();
        Order o1=new Order(1,"shopA",new BigDecimal(10));
        Order o2=new Order(2,"shopB",new BigDecimal(10));
        Order o3=new Order(3,"shopC",new BigDecimal(10));
        Order o4=new Order(4,"shopC",new BigDecimal(10));
        Order o5=new Order(5, "shopC", new BigDecimal("10"));
        Order o6= new Order(6,"shopC",new BigDecimal("10"));
        l.add(o1);
        l.add(o2);
        l.add(o3);
        l.add(o4);
        l.add(o5);
        l.add(o6);
        return  l;
    }
    private static List<Order> getShopList(List<Order> l){
        Map<String,List<Order>> map=new HashMap<>();
        Map<String, BigDecimal> val=new HashMap<>();
        for(Order o:l){
            List<Order> list=map.getOrDefault(o.getShop(),new ArrayList<Order>());
            BigDecimal  v=val.getOrDefault(o.getShop(),new BigDecimal("0"));
            v=v.add(o.getVal());
            val.put(o.getShop(),v);
            list.add(o);
            map.put(o.getShop(),list);
        }
        for(Map.Entry<String, List<Order>> e:map.entrySet()){
            System.out.println(e.getKey()+"------"+e.getValue());
        }
        for(Map.Entry<String,BigDecimal> e :val.entrySet()){
            System.out.println(e.getKey()+"------"+e.getValue());
        }
        return null;
    }


    private static int  getNum(int n){
      int  arr[]=new int[n];
      int  index3=0,index5=0,index7=0,index=1;
      arr[0]=1;
      while(index<n){
          int minNum=getMin(arr[index3]*3,arr[index5]*5,arr[index7]*7);
          arr[index++]=minNum;
          while(arr[index3]*3<=minNum) index3++;
          while(arr[index5]*5<=minNum) index5++;
          while(arr[index7]*7<=minNum) index7++;
      }
      return arr[n-1];
    }
    private static  int getMin(int a,int b,int c){
           a=a<b?a:b;
      return a<c?a:c;
    }
    private static int  getAns(int nums[]){
        if(nums.length==0) {
            return 0;
        }
        if(nums.length ==1 ){
            return nums[1];
        }

        int num1=nums[0],num2=Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++){
            int num3=Math.max(num1+nums[i], num2);
            num1=num2;
            num2=num3;
        }
        return num2;
    }

    @org.junit.Test
    public void test(){

    }


    public boolean exist(char[][] board, String word) {
        char []c=word.toCharArray();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(c[0]==board[i][j]){
                    if(dfs(0,0,board,c,0)) return true;
                }
            }
        }

        return false;

    }

    private boolean dfs(int i,int j,char[][] board,char word[],int idx){
        if(i<0 ||j<0 || i>=board.length || j>=board[0].length || board[i][j]!=word[idx]) return false;
        if(idx==word.length-1) return true;
        char c=word[idx];
        word[idx]='-';
        boolean flag=false;
        flag=dfs(i+1,j,board,word,idx+1);
        if(flag) return true;
        flag=dfs(i-1,j,board,word,idx+1);
        if(flag) return true;
        flag=dfs(i,j+1,board,word,idx+1);
        if(flag) return true;
        flag=dfs(i,j-1,board,word,idx+1);
        if(flag) return true;
        word[idx]=c;
        return false;
    }


    @Data
    @AllArgsConstructor
    static class Order{
        int id;
        String shop;
        BigDecimal val;

        public Order() {

        }
    }
}
