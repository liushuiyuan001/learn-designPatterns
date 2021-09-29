import java.util.Observer;

public class RedheadDuck implements Quackable{
    @Override
    public void quack() {
        System.out.println("Redhead Quack");
    }

    @Override
    public void registerObserver(CustomObserver observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
