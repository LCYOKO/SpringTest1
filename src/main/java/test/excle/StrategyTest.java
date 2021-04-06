package test.excle;

/**
 * @Author l
 * @Date 2021/3/8 23:04
 * @Version 1.0
 */
import java.util.HashMap;
import java.util.Map;
public class StrategyTest {

     public void test(){

     }


}


 interface Strategy{

    default double getCost(){
        return 0;
    }
}

class StrategyFactory{
    private static Map<String,Strategy> strategies;

    private void init(){
        strategies=new HashMap<>(32);
    }

    public static Strategy getStrategy(String condition){
        Strategy strategy=strategies.get(condition);
        if(strategy==null){
            throw new RuntimeException("error");
        }
        return strategy;
    }

}


class ExpressStrategy implements Strategy{
    @Override
    public double getCost() {
        return 0;
    }
}


class TruckStrategy implements Strategy{
    @Override
    public double getCost() {
        return 0;
    }
}


