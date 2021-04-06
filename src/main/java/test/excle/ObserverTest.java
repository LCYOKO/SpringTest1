package test.excle;

/**
 * @Author l
 * @Date 2021/3/8 23:12
 * @Version 1.0
 */
public class ObserverTest {
}



interface Observer{
    void update(String str);
}

class ObserverImpl01 implements Observer{
    @Override
    public void update(String str) {

    }
}

class ObserverImpl02 implements  Observer{

    @Override
    public void update(String str) {

    }
}


interface Subject{
    void add();
    void update();
    void notifyObserver();
}

class SubjectImpl implements Subject{

    @Override
    public void add() {

    }

    @Override
    public void update() {

    }

    @Override
    public void notifyObserver() {

    }
}
