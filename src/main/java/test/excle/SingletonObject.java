package test.excle;

/**
 * @Author l
 * @Date 2021/3/8 23:00
 * @Version 1.0
 */
public class SingletonObject {

    private SingletonObject object;
    private SingletonObject(){

    }

    public SingletonObject getObject(){
        if(object==null) {
            synchronized (this) {
                  if(object==null){
                      object=new SingletonObject();
                  }
            }

        }
        return  object;
    }


    private static class SingletonObjectHolder{
        private static SingletonObject object=new SingletonObject();

    }
}
