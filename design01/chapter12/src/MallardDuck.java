import java.util.Observer;

public class MallardDuck implements Quackable{
    Observable observable;

    public MallardDuck() {
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("Mallard Quack");
        notifyObservers();
    }

    @Override
    public void registerObserver(CustomObserver observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }
}
