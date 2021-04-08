package test.jdk8;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * @Author l
 * @Date 2021/4/7 17:49
 * @Version 1.0
 */
public class LambdaTest {

    public static void printNum(Integer data){
        System.out.println(data);
    }
    public static int returnNum3(){
        return 3;
    }
    @Test
    public  void test01(){
        int[] arr={1,2,3,4,5};

        List<Integer> list = Arrays.asList(1,2,3,4);
        Map<String, Object> map = new HashMap<>();
        map.put("123","123");
        map.put("456","456");
        map.entrySet().stream().forEach(System.out::println);
        HashSet<Integer> set = new HashSet<>();
        Stream.iterate(0,n->n+2).limit(10).forEach(System.out::println);
        //distinct().
                //limit(1).
                //        collect(Collectors.toList());
    }


    @Test
    public void practice(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
       List<Integer> l= new ArrayList<>();
       l.add(1);
       l.add(2);
       l.add(3);
       process02(transactions);

    }

    private void process01(List<Integer> list){
        //Double aDouble = list.stream().collect(Collectors.g);
        //System.out.println(aDouble);
        int[] array = list.stream().mapToInt(e -> e.intValue()).toArray();
    }

    private void process02(List<Transaction> transactions){
        Map<Trader, List<Transaction>> map = transactions.stream().collect(Collectors.groupingBy(e -> e.getTrader()));
        System.out.println(map);
    }

    private void testSupplyFunction(Supplier<Integer> supplier){
        System.out.println(supplier.get());
    }
    private void testConsumerFunction(int[] arr, Consumer<Integer> consumer){
        for(int e:arr){
            consumer.accept(e);
        }
    }
}
