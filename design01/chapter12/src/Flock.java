import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;

public class Flock implements Quackable{
    ArrayList<Quackable> quackers = new ArrayList<Quackable>();
    Observable observable;

    public Flock() {
        observable = new Observable(this);
    }
    public void add(Quackable quackable) {
        quackers.add(quackable);
    }
    public void quack() {
        Iterator<Quackable> iterator = quackers.iterator();
        while (iterator.hasNext()) {
            Quackable quackable= (Quackable) iterator.next();
            quackable.quack();
        }
        notifyObservers();
    }

    @Override
    public void registerObserver(CustomObserver observer) {
        System.out.println("????????");
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        System.out.println("1111111111");
        observable.notifyObservers();
    }
}
